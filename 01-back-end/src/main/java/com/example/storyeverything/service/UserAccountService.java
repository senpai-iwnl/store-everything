package com.example.storyeverything.service;

import com.example.storyeverything.dto.UserAccountDTO;
<<<<<<< HEAD
=======
import com.example.storyeverything.model.UserAccount;
>>>>>>> 6f3763a0ef61fd30eacb6d3fcb695bef2b8d9ec4

import java.util.List;

public interface UserAccountService {
    List<UserAccountDTO> findAll();
    UserAccountDTO findById(long id);
<<<<<<< HEAD
    UserAccountDTO createAsUser(UserAccountDTO userAccountDTO);
    UserAccountDTO createAsAdmin(UserAccountDTO userAccountDTO);
    UserAccountDTO updateAsUser(long id, UserAccountDTO userAccountDTO);
    UserAccountDTO updateAsAdmin(long id, UserAccountDTO userAccountDTO);
=======
    UserAccountDTO create(UserAccountDTO userAccountDTO);
    UserAccountDTO update(long id, UserAccountDTO userAccountDTO);
    UserAccountDTO updateRole(long id, String name);
>>>>>>> 6f3763a0ef61fd30eacb6d3fcb695bef2b8d9ec4
    void deleteById(long id);
}
