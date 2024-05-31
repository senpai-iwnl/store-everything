package com.example.storyeverything.service.impl;

import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.exception.DuplicateLoginException;
import com.example.storyeverything.exception.FieldNotFoundException;
import com.example.storyeverything.mapper.UserAccountMapper;
import com.example.storyeverything.model.Role;
import com.example.storyeverything.model.UserAccount;
import com.example.storyeverything.repository.InformationRepository;
import com.example.storyeverything.repository.RoleRepository;
import com.example.storyeverything.repository.UserAccountRepository;
import com.example.storyeverything.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final InformationRepository informationRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository,
                                  UserAccountMapper userAccountMapper,
                                  RoleRepository roleRepository,
                                  PasswordEncoder passwordEncoder,
                                  InformationRepository informationRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userAccountMapper = userAccountMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.informationRepository = informationRepository;
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
    public UserAccountDTO createAsUser(UserAccountDTO userAccountDTO) {
        if (!isLoginAvailable(userAccountDTO.getLogin())) {
            throw new DuplicateLoginException("Login is already in use");
        }

        userAccountDTO.setRole("LIMITED_USER");

        userAccountDTO.setPassword(encodingPassword(userAccountDTO.getPassword()));

        UserAccount userAccount = userAccountMapper.toEntity(userAccountDTO, roleRepository, informationRepository);

        Role userRole = roleRepository.findByName("ROLE_LIMITED_USER")
                .orElseThrow(() -> new FieldNotFoundException("Role", "name", "ROLE_LIMITED_USER"));
        userAccount.setRole(userRole);

        userAccount = userAccountRepository.save(userAccount);

        return userAccountMapper.toDTO(userAccount);
    }

    @Override
    public UserAccountDTO createAsAdmin(UserAccountDTO userAccountDTO) {
        if (!isLoginAvailable(userAccountDTO.getLogin())) {
            throw new DuplicateLoginException("Login is already in use");
        }

        UserAccount userAccount = userAccountMapper.toEntity(userAccountDTO, roleRepository, informationRepository);
        // default user role
        Role newRole = roleRepository.findByName(userAccountDTO.getRole())
                .orElseThrow(() -> new FieldNotFoundException("Role", "id", userAccountDTO.getRole()));

        userAccount.setRole(newRole);
        userAccount = userAccountRepository.save(userAccount);

        return userAccountMapper.toDTO(userAccount);
    }

    @Override
    @Transactional
    public UserAccountDTO updateAsUser(long id, UserAccountDTO userAccountDTO) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("UserAccount", "id", id));


        if (!userAccount.getLogin().equals(userAccountDTO.getLogin()) && !isLoginAvailable(userAccountDTO.getLogin())) {
            throw new DuplicateLoginException("Login is already in use");
        }

        if(userAccountDTO.getPassword() != null)
            userAccountDTO.setPassword(encodingPassword(userAccountDTO.getPassword()));
        else
            userAccountDTO.setPassword(userAccount.getPassword());

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

        if(userAccountDTO.getPassword() != null)
            userAccountDTO.setPassword(encodingPassword(userAccountDTO.getPassword()));
        else
            userAccountDTO.setPassword(userAccount.getPassword());


        Role newRole = roleRepository.findByName(userAccountDTO.getRole()).orElseThrow(() -> new FieldNotFoundException("Role", "name", userAccountDTO.getRole()));
        userAccount.setRole(newRole);


        userAccountMapper.updateUserAccountFromDTOAsAdmin(userAccountDTO, userAccount, roleRepository, informationRepository);

        return userAccountMapper.toDTO(userAccount);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        if(userAccountRepository.findById(id).isEmpty())
            throw new FieldNotFoundException("UserAccount", "id", id);

        userAccountRepository.deleteById(id);
    }

    private boolean isLoginAvailable(String login) {
        return !userAccountRepository.findByLogin(login).isPresent();
    }

    private String encodingPassword(String password){
        return passwordEncoder.encode(password);
    }
}
