package com.example.storyeverything.service;

import com.example.storyeverything.dto.InformationDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InformationService {
    List<InformationDTO> findAll(String login);
    Page<InformationDTO> findAllWithPagination(String login, int page, int size, String sortBy, String direction);
    InformationDTO findById(Long id, String login);
    InformationDTO create(InformationDTO informationDTO, String login);
    InformationDTO update(Long id, InformationDTO informationDTO, String login);
    void delete(Long id, String login);
}
