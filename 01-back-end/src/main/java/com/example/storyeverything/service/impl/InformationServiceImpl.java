package com.example.storyeverything.service.impl;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.exception.FieldNotFoundException;
import com.example.storyeverything.mapper.InformationMapper;
import com.example.storyeverything.model.Category;
import com.example.storyeverything.model.Information;
import com.example.storyeverything.model.UserAccount;
import com.example.storyeverything.repository.CategoryRepository;
import com.example.storyeverything.repository.InformationRepository;
import com.example.storyeverything.repository.UserAccountRepository;
import com.example.storyeverything.service.InformationService;
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
    public List<InformationDTO> findAll() {
        List<Information> information = informationRepository.findAll();
        return informationMapper.toDTOList(information);
    }

    @Override
    public List<InformationDTO> findByAccountUserId(long id) {
        List<Information> information = informationRepository.findAllByUserAccountId(id);
        return informationMapper.toDTOList(information);
    }

    @Override
    public List<InformationDTO> findByCategoryId(long id) {
        List<Information> information = informationRepository.findAllByCategoryId(id);
        return informationMapper.toDTOList(information);
    }

    @Override
    public InformationDTO findById(long id) {
        Information information = informationRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Information", "id", id));
        return informationMapper.toDTO(information);
    }

    @Override
    public InformationDTO create(InformationDTO informationDTO) {
        Information information = informationMapper.toEntity(informationDTO);
        information = informationRepository.save(information);
        return informationMapper.toDTO(information);
    }

    @Override
    @Transactional
    public InformationDTO update(long id, InformationDTO informationDTO) {
        Information information = informationRepository.findById(id)
                .orElseThrow(()-> new FieldNotFoundException("Information", "id", id));

        long userAccountId = informationDTO.getUserAccountId();
        UserAccount userAccount = userAccountRepository.findById(userAccountId)
                .orElseThrow(()-> new FieldNotFoundException("UserAccount", "id", userAccountId));

        String categoryName = informationDTO.getCategoryName();
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(()-> new FieldNotFoundException("Category", "name", categoryName));

        information.setUserAccount(userAccount);
        information.setCategory(category);

        informationMapper.updateInformationFromDTO(informationDTO, information);
        return informationMapper.toDTO(information);
    }

    @Override
    public void deleteById(long id) {
        if(informationRepository.findById(id).isEmpty())
            throw new FieldNotFoundException("Information", "id", id);

        informationRepository.deleteById(id);
    }
}
