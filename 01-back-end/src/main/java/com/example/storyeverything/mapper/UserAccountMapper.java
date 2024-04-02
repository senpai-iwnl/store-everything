package com.example.storyeverything.mapper;

import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccountMapper INSTANCE = Mappers.getMapper(UserAccountMapper.class);
    @Mapping(target = "roleId", source = "role.id")
    UserAccountDTO toDTO(UserAccount userAccount);
    List<UserAccountDTO> toDTOList(List<UserAccount> userAccounts);
    @Mapping(target = "role.id", ignore = true)
    UserAccount toEntity(UserAccountDTO userAccountDTO);
    @Mapping(target = "id", ignore = true)
    void updateUserAccountFromDTO(UserAccountDTO userAccountDTO, @MappingTarget UserAccount userAccount);
}
