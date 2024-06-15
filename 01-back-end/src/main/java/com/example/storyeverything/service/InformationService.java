package com.example.storyeverything.service;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.model.Information;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InformationService {
    List<InformationDTO> findAllByLogin(String login);
    List<InformationDTO> findAllPublic(String login);
    Page<InformationDTO> findAllByLoginWithPagination(String login, int page, int size, String sortBy, String direction);
    InformationDTO findByIdAndLogin(Long id, String login);
    InformationDTO findById(Long id);
    InformationDTO create(InformationDTO informationDTO, String login);
    InformationDTO update(Long id, InformationDTO informationDTO, String login);
    void delete(Long id, String login);
}
