package com.example.storyeverything.mapper;

import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.model.Role;
import com.example.storyeverything.model.UserAccount;
import com.example.storyeverything.repository.RoleRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccountMapper INSTANCE = Mappers.getMapper(UserAccountMapper.class);

    @Mapping(target = "role", source = "role.name")
    @Mapping(target = "password", ignore = true)
    UserAccountDTO toDTO(UserAccount userAccount);

    List<UserAccountDTO> toDTOList(List<UserAccount> userAccounts);

    @Mapping(target = "role", expression = "java(mapRoleNameToRole(userAccountDTO.getRole(), roleRepository))")
    UserAccount toEntity(UserAccountDTO userAccountDTO, @Context RoleRepository roleRepository);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", expression = "java(mapRoleNameToRole(userAccountDTO.getRole(), roleRepository))")
    void updateUserAccountFromDTOAsAdmin(UserAccountDTO userAccountDTO, @MappingTarget UserAccount userAccount, @Context RoleRepository roleRepository);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateUserAccountFromDTOAsUser(UserAccountDTO userAccountDTO, @MappingTarget UserAccount userAccount);

    default Role mapRoleNameToRole(String roleName, RoleRepository roleRepository) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
    }

    default String mapRoleToRoleName(Role role) {
        return role.getName();
    }
}