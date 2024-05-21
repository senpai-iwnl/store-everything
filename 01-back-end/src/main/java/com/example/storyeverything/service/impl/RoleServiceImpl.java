package com.example.storyeverything.service.impl;

import com.example.storyeverything.exception.FieldNotFoundException;
import com.example.storyeverything.model.Role;
import com.example.storyeverything.repository.RoleRepository;
import com.example.storyeverything.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public String getRoleNameById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Role", "id", id));

        return role.getName();
    }
}
