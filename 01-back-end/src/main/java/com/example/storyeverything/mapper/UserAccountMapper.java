package com.example.storyeverything.mapper;

import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.model.Information;
import com.example.storyeverything.model.Role;
import com.example.storyeverything.model.UserAccount;
import com.example.storyeverything.repository.InformationRepository;
import com.example.storyeverything.repository.RoleRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {InformationMapper.class})
public interface UserAccountMapper {

    UserAccountMapper INSTANCE = Mappers.getMapper(UserAccountMapper.class);

    @Mapping(target = "role", source = "role.name")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "informationIds", source = "information", qualifiedByName = "mapInformationToIds")
    UserAccountDTO toDTO(UserAccount userAccount);

    List<UserAccountDTO> toDTOList(List<UserAccount> userAccounts);

    @Mapping(target = "role", expression = "java(mapRoleNameToRole(userAccountDTO.getRole(), roleRepository))")
    @Mapping(target = "information", source = "informationIds", qualifiedByName = "mapIdsToInformation")
    UserAccount toEntity(UserAccountDTO userAccountDTO, @Context RoleRepository roleRepository, @Context InformationRepository informationRepository);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", expression = "java(mapRoleNameToRole(userAccountDTO.getRole(), roleRepository))")
    @Mapping(target = "information", source = "informationIds", qualifiedByName = "mapIdsToInformation")
    void updateUserAccountFromDTOAsAdmin(UserAccountDTO userAccountDTO, @MappingTarget UserAccount userAccount, @Context RoleRepository roleRepository, @Context InformationRepository informationRepository);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateUserAccountFromDTOAsUser(UserAccountDTO userAccountDTO, @MappingTarget UserAccount userAccount);

    default Role mapRoleNameToRole(String roleName, RoleRepository roleRepository) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
    }

    @Named("mapInformationToIds")
    default Set<Long> mapInformationToIds(Set<Information> information) {
        return information.stream()
                .map(Information::getId)
                .collect(Collectors.toSet());
    }

    @Named("mapIdsToInformation")
    default Set<Information> mapIdsToInformation(Set<Long> ids, @Context InformationRepository informationRepository) {
        if (ids == null) return Collections.emptySet();
        return ids.stream()
                .map(id -> informationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Information not d: " + id)))
                .collect(Collectors.toSet());
    }

    default String mapRoleToRoleName(Role role) {
        return role.getName();
    }
}
