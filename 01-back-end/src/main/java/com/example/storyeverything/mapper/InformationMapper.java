package com.example.storyeverything.mapper;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.model.Information;
import com.example.storyeverything.model.UserAccount;
import com.example.storyeverything.repository.UserAccountRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface InformationMapper {
    InformationMapper INSTANCE = Mappers.getMapper(InformationMapper.class);
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "userAccountId", source = "userAccount.id")
    @Mapping(target = "addDate", ignore = true)
    InformationDTO toDTO(Information information);
    List<InformationDTO> toDTOList(List<Information> information);
    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "userAccount.id", source = "userAccountId")
    @Mapping(target = "addDate", ignore = true)
    Information toEntity(InformationDTO informationDTO);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addDate", ignore = true)
    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "userAccount.id", source = "userAccountId")
    void updateUserAccountFromDTO(InformationDTO informationDTO, @MappingTarget Information information);

}
