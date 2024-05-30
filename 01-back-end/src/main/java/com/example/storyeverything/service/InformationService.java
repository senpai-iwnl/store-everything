package com.example.storyeverything.service;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.model.Information;

import java.util.List;

public interface InformationService {
    List<InformationDTO> findAllByLogin(String login);
    InformationDTO findByIdAndLogin(Long id, String login);
    InformationDTO create(InformationDTO informationDTO, String login);
    InformationDTO update(Long id, InformationDTO informationDTO, String login);
    void delete(Long id, String login);
}
