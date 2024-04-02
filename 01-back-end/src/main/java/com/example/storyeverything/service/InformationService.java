package com.example.storyeverything.service;

import com.example.storyeverything.dto.InformationDTO;

import java.util.List;

public interface InformationService {
    List<InformationDTO> findAll();
    List<InformationDTO> findByAccountUserId(long id);
    List<InformationDTO> findByCategoryId(long id);
    InformationDTO findById(long id);
    InformationDTO create(InformationDTO informationDTO);
    InformationDTO update(long id, InformationDTO informationDTO);
    void deleteById(long id);
}
