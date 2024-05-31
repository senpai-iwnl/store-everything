package com.example.storyeverything.service.impl;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.exception.FieldNotFoundException;
import com.example.storyeverything.exception.InformationAccessDeniedException;
import com.example.storyeverything.mapper.InformationMapper;
import com.example.storyeverything.model.Category;
import com.example.storyeverything.model.Information;
import com.example.storyeverything.model.UserAccount;
import com.example.storyeverything.model.UserAccountInformation;
import com.example.storyeverything.repository.CategoryRepository;
import com.example.storyeverything.repository.InformationRepository;
import com.example.storyeverything.repository.UserAccountInformationRepository;
import com.example.storyeverything.repository.UserAccountRepository;
import com.example.storyeverything.service.InformationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InformationServiceImpl implements InformationService {
    private final InformationRepository informationRepository;
    private final InformationMapper informationMapper;
    private final UserAccountRepository userAccountRepository;
    private final CategoryRepository categoryRepository;
    private final UserAccountInformationRepository userAccountInformationRepository;

    public InformationServiceImpl(InformationRepository informationRepository,
                                  InformationMapper informationMapper,
                                  UserAccountRepository userAccountRepository,
                                  CategoryRepository categoryRepository,
                                  UserAccountInformationRepository userAccountInformationRepository) {
        this.informationRepository = informationRepository;
        this.informationMapper = informationMapper;
        this.userAccountRepository = userAccountRepository;
        this.categoryRepository = categoryRepository;
        this.userAccountInformationRepository = userAccountInformationRepository;
    }

    @Override
    public List<InformationDTO> findAll(String login) {
        UserAccount userAccount = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login));
        List<Information> information = informationRepository.findAllByUserAccountsContains(userAccount);
        return informationMapper.toDTOList(information);
    }

    @Override
    public Page<InformationDTO> findAllWithPagination(String login, int page, int size, String sortBy, String direction) {
        UserAccount userAccount = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login));

        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Information> informationPage = informationRepository.findAllByUserAccountsContains(userAccount, pageable);
        return informationPage.map(informationMapper::toDTO);
    }

    @Override
    public InformationDTO findById(Long id, String login) {
        Information information = informationRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Information", "id", id.toString()));
        UserAccount userAccount = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login));

        boolean isAssociated = userAccountInformationRepository.findByUserAccountAndInformation(userAccount, information).isPresent();

        if (information.getPublic() || isAssociated) {
            return informationMapper.toDTO(information);
        } else {
            throw new InformationAccessDeniedException(login, id.toString());
        }
    }


    @Override
    public InformationDTO create(InformationDTO informationDTO, String login) {
        UserAccount userAccount = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login));

        Information information = informationMapper.toEntity(informationDTO, categoryRepository, userAccountRepository);
        Category category = categoryRepository.findByName(informationDTO.getCategoryName())
                .orElseThrow(() -> new FieldNotFoundException("Category", "name", informationDTO.getCategoryName()));

        information.setCategory(category);
        Set<UserAccount> userAccounts = new HashSet<>();
        userAccounts.add(userAccount);
        information.setUserAccounts(userAccounts);

        Information savedInformation = informationRepository.save(information);

        UserAccountInformation userAccountInformation = new UserAccountInformation(userAccount, savedInformation, true);
        userAccountInformationRepository.save(userAccountInformation);

        return informationMapper.toDTO(savedInformation);
    }

    @Override
    @Transactional
    public InformationDTO update(Long id, InformationDTO informationDTO, String login) {
        UserAccount userAccount = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login));

        UserAccountInformation userAccountInformation = userAccountInformationRepository.findByUserAccountAndInformation(userAccount, informationRepository.findById(id)
                        .orElseThrow(() -> new FieldNotFoundException("Information", "id", id.toString())))
                .orElseThrow(() -> new InformationAccessDeniedException(login, id.toString()));

        if (!userAccountInformation.isOwner()) {
            throw new InformationAccessDeniedException(login, id.toString());
        }

        Information existingInformation = userAccountInformation.getInformation();

        Category category = categoryRepository.findByName(informationDTO.getCategoryName())
                .orElseThrow(() -> new FieldNotFoundException("Category", "name", informationDTO.getCategoryName()));

        existingInformation.setCategory(category);

        informationMapper.updateInformationFromDTO(informationDTO, existingInformation, categoryRepository, userAccountRepository);

        Information updatedInformation = informationRepository.save(existingInformation);
        return informationMapper.toDTO(updatedInformation);
    }

    @Override
    @Transactional
    public void delete(Long id, String login) {
        UserAccount userAccount = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login));

        Information information = informationRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Information", "id", id.toString()));

        UserAccountInformation userAccountInformation = userAccountInformationRepository.findByUserAccountAndInformation(userAccount, information)
                .orElseThrow(() -> new InformationAccessDeniedException(login, id.toString()));

        if (!userAccountInformation.isOwner()) {
            throw new InformationAccessDeniedException(login, id.toString());
        }

        // Remove the relationships from user_account_information
        userAccountInformationRepository.delete(userAccountInformation);

        // Check if there are any other relationships for this information
        List<UserAccountInformation> remainingRelationships = userAccountInformationRepository.findByInformation(information);
        if (remainingRelationships.isEmpty()) {
            // If no other relationships, delete the information
            informationRepository.delete(information);
        }
    }
}
