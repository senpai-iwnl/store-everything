package com.example.storyeverything.mapper;

import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccountMapper INSTANCE = Mappers.getMapper(UserAccountMapper.class);
    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "password", ignore = true)
    UserAccountDTO toDTO(UserAccount userAccount);
    List<UserAccountDTO> toDTOList(List<UserAccount> userAccounts);
    UserAccount toEntity(UserAccountDTO userAccountDTO);
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "roleId", target = "role.id")
    void updateUserAccountFromDTOAsAdmin(UserAccountDTO userAccountDTO, @MappingTarget UserAccount userAccount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateUserAccountFromDTOAsUser(UserAccountDTO userAccountDTO, @MappingTarget UserAccount userAccount);

}
