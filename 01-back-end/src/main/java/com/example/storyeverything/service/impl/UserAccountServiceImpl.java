package com.example.storyeverything.service.impl;

import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.exception.DuplicateLoginException;
import com.example.storyeverything.exception.FieldNotFoundException;
import com.example.storyeverything.mapper.UserAccountMapper;
import com.example.storyeverything.model.Role;
import com.example.storyeverything.model.UserAccount;
import com.example.storyeverything.repository.RoleRepository;
import com.example.storyeverything.repository.UserAccountRepository;
import com.example.storyeverything.service.UserAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final UserAccountMapper userAccountMapper;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository,
                                  UserAccountMapper userAccountMapper,
                                  RoleRepository roleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userAccountMapper = userAccountMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserAccountDTO> findAll() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();

        return userAccountMapper.toDTOList(userAccounts);
    }

    @Override
    public UserAccountDTO findById(long id) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "id", id));

        return userAccountMapper.toDTO(userAccount);
    }

    @Override
<<<<<<< HEAD
    public UserAccountDTO createAsUser(UserAccountDTO userAccountDTO) {
=======
    public UserAccountDTO create(UserAccountDTO userAccountDTO) {
>>>>>>> 6f3763a0ef61fd30eacb6d3fcb695bef2b8d9ec4
        if (!isLoginAvailable(userAccountDTO.getLogin())) {
            throw new DuplicateLoginException("Login is already in use");
        }

        UserAccount userAccount = userAccountMapper.toEntity(userAccountDTO);
        // default user role
        userAccount.setRole(roleRepository.getById(2L));
        userAccount = userAccountRepository.save(userAccount);

        return userAccountMapper.toDTO(userAccount);
    }

    @Override
<<<<<<< HEAD
    public UserAccountDTO createAsAdmin(UserAccountDTO userAccountDTO) {
=======
    @Transactional
    public UserAccountDTO update(long id, UserAccountDTO userAccountDTO) {
>>>>>>> 6f3763a0ef61fd30eacb6d3fcb695bef2b8d9ec4
        if (!isLoginAvailable(userAccountDTO.getLogin())) {
            throw new DuplicateLoginException("Login is already in use");
        }

<<<<<<< HEAD
        UserAccount userAccount = userAccountMapper.toEntity(userAccountDTO);
        // default user role
        Role newRole = roleRepository.findById(userAccountDTO.getRoleId())
                .orElseThrow(() -> new FieldNotFoundException("Role", "id", userAccountDTO.getRoleId()));

        userAccount.setRole(newRole);
=======
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "id", id));

        userAccountMapper.updateUserAccountFromDTO(userAccountDTO, userAccount);
>>>>>>> 6f3763a0ef61fd30eacb6d3fcb695bef2b8d9ec4
        userAccount = userAccountRepository.save(userAccount);

        return userAccountMapper.toDTO(userAccount);
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public UserAccountDTO updateAsUser(long id, UserAccountDTO userAccountDTO) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "id", id));


        if (!userAccount.getLogin().equals(userAccountDTO.getLogin()) && !isLoginAvailable(userAccountDTO.getLogin())) {
            throw new DuplicateLoginException("Login is already in use");
        }


        userAccountMapper.updateUserAccountFromDTOAsUser(userAccountDTO, userAccount);
        userAccount = userAccountRepository.save(userAccount);

        return userAccountMapper.toDTO(userAccount);
    }

    @Override
    @Transactional
    public UserAccountDTO updateAsAdmin(long id, UserAccountDTO userAccountDTO) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "id", id));


        if (!userAccount.getLogin().equals(userAccountDTO.getLogin()) && !isLoginAvailable(userAccountDTO.getLogin())) {
            throw new DuplicateLoginException("Login is already in use");
        }



        Role newRole = roleRepository.findById(userAccountDTO.getRoleId())
                .orElseThrow(() -> new FieldNotFoundException("Role", "id", userAccountDTO.getRoleId()));

        userAccount.setRole(newRole);

        userAccountMapper.updateUserAccountFromDTOAsAdmin(userAccountDTO, userAccount);
=======
    public UserAccountDTO updateRole(long id, String role) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "id", id));
        Role newRole = roleRepository.findByRole(role)
                .orElseThrow(() -> new FieldNotFoundException("Role", "name", role));

        userAccount.setRole(newRole);
>>>>>>> 6f3763a0ef61fd30eacb6d3fcb695bef2b8d9ec4
        userAccount = userAccountRepository.save(userAccount);

        return userAccountMapper.toDTO(userAccount);
    }

    @Override
    public void deleteById(long id) {
        if(userAccountRepository.findById(id).isEmpty())
            throw new FieldNotFoundException("UserAccount", "id", id);

        userAccountRepository.deleteById(id);
    }

    private boolean isLoginAvailable(String login) {
        return !userAccountRepository.findByLogin(login).isPresent();
    }
}
