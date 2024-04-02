package com.example.storyeverything.service;

import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.model.UserAccount;

import java.util.List;

public interface UserAccountService {
    List<UserAccountDTO> findAll();
    UserAccountDTO findById(long id);
    UserAccountDTO create(UserAccountDTO userAccountDTO);
    UserAccountDTO update(long id, UserAccountDTO userAccountDTO);
    UserAccountDTO updateRole(long id, String name);
    void deleteById(long id);
}
