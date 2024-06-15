package com.example.storyeverything.service.impl;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.exception.FieldNotFoundException;
import com.example.storyeverything.exception.InformationAccessDeniedException;
import com.example.storyeverything.mapper.InformationMapper;
import com.example.storyeverything.model.Category;
import com.example.storyeverything.model.Information;
import com.example.storyeverything.model.UserAccount;
import com.example.storyeverything.repository.CategoryRepository;
import com.example.storyeverything.repository.InformationRepository;
import com.example.storyeverything.repository.UserAccountRepository;
import com.example.storyeverything.service.InformationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class InformationServiceImpl implements InformationService {
    private final InformationRepository informationRepository;
    private final InformationMapper informationMapper;
    private final UserAccountRepository userAccountRepository;
    private final CategoryRepository categoryRepository;

    public InformationServiceImpl(InformationRepository informationRepository,
                                  InformationMapper informationMapper,
                                  UserAccountRepository userAccountRepository,
                                  CategoryRepository categoryRepository) {
        this.informationRepository = informationRepository;
        this.informationMapper = informationMapper;
        this.userAccountRepository = userAccountRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<InformationDTO> findAllByLogin(String login) {
        Long userAccountId = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login)).getId();
        List<Information> information = informationRepository.findAllByUserAccountId(userAccountId);
        return informationMapper.toDTOList(information);
    }
    public List<InformationDTO> findAllPublic(String login) {
        List<Information> information = informationRepository.findAllByIsPublic(true);
        return informationMapper.toDTOList(information);
    }

    @Override
    public Page<InformationDTO> findAllByLoginWithPagination(String login, int page, int size, String sortBy, String direction) {
        Long userAccountId = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login)).getId();

        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Information> informationPage = informationRepository.findAllByUserAccountId(userAccountId, pageable);
        return informationPage.map(informationMapper::toDTO);
    }

    @Override
    public InformationDTO findByIdAndLogin(Long id, String login) {
        Information information = informationRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Information", "id", id.toString()));
        Long userAccountId = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login)).getId();

        if(information.getIsPublic()){
            return informationMapper.toDTO(information);
        } else if (informationRepository.findByIdAndUserAccountId(id, userAccountId).isPresent()) {
            return informationMapper.toDTO(information);
        } else {
            throw new InformationAccessDeniedException(login, id.toString());
        }
    }

    @Override
    public InformationDTO findById(Long id) {
        Information information = informationRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Information", "id", id.toString()));
        if(information.getIsPublic()){
            return informationMapper.toDTO(information);
        //}// else if (informationRepository.findById(id).isPresent()) {
        //    return informationMapper.toDTO(information);
        } else {
            throw new InformationAccessDeniedException("anonymous", id.toString());
        }
    }

    @Override
    public InformationDTO create(InformationDTO informationDTO, String login) {
        UserAccount userAccount = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login));

        Information information = informationMapper.toEntity(informationDTO, categoryRepository);
        Category category = categoryRepository.findByName(informationDTO.getCategoryName())
                .orElseThrow(() -> new FieldNotFoundException("Category", "name", informationDTO.getCategoryName()));

        information.setCategory(category);
        information.setUserAccount(userAccount);

        Information savedInformation = informationRepository.save(information);
        return informationMapper.toDTO(savedInformation);
    }

    @Override
    @Transactional
    public InformationDTO update(Long id, InformationDTO informationDTO, String login) {
        Long userAccountId = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login)).getId();
        Information existingInformation = informationRepository.findByIdAndUserAccountId(id, userAccountId)
                .orElseThrow(() -> new FieldNotFoundException("Information", "id", id.toString()));

        Category category = categoryRepository.findByName(informationDTO.getCategoryName())
                .orElseThrow(() -> new FieldNotFoundException("Category", "name", informationDTO.getCategoryName()));

        existingInformation.setCategory(category);

        informationMapper.updateInformationFromDTO(informationDTO, existingInformation, categoryRepository);


        Information updatedInformation = informationRepository.save(existingInformation);
        return informationMapper.toDTO(updatedInformation);
    }

    @Override
    @Transactional
    public void delete(Long id, String login) {
        Long userAccountId = userAccountRepository.findByLogin(login)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "login", login)).getId();

        Information existingInformation = informationRepository.findByIdAndUserAccountId(id, userAccountId)
                .orElseThrow(() -> new FieldNotFoundException("Information", "id", id.toString()));

        informationRepository.delete(existingInformation);
    }
}
