package com.example.storyeverything.service;

import com.example.storyeverything.dto.UserAccountDTO;

import java.util.List;

public interface UserAccountService {
    List<UserAccountDTO> findAll();
    UserAccountDTO findById(long id);
    UserAccountDTO findByToken(String login);
    UserAccountDTO createAsUser(UserAccountDTO userAccountDTO);
    UserAccountDTO createAsAdmin(UserAccountDTO userAccountDTO);
    UserAccountDTO updateAsUser(long id, UserAccountDTO userAccountDTO);
    UserAccountDTO updateAsAdmin(long id, UserAccountDTO userAccountDTO);
    void deleteById(long id);
}
