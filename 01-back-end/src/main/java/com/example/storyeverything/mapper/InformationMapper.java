package com.example.storyeverything.mapper;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.model.Category;
import com.example.storyeverything.model.Information;
import com.example.storyeverything.model.UserAccount;
import com.example.storyeverything.repository.CategoryRepository;
import com.example.storyeverything.repository.UserAccountRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InformationMapper {

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "userAccounts", target = "userAccountIds", qualifiedByName = "mapUserAccountsToIds")
    InformationDTO toDTO(Information information);

    List<InformationDTO> toDTOList(List<Information> informationList);

    @Mapping(target = "category", source = "categoryName", qualifiedByName = "mapCategoryNameToCategory")
    @Mapping(source = "userAccountIds", target = "userAccounts", qualifiedByName = "mapIdsToUserAccounts")
    Information toEntity(InformationDTO informationDTO, @Context CategoryRepository categoryRepository, @Context UserAccountRepository userAccountRepository);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addDate", ignore = true)
    @Mapping(target = "category", expression = "java(mapCategoryNameToCategory(dto.getCategoryName(), categoryRepository))")
    void updateInformationFromDTO(InformationDTO dto, @MappingTarget Information information, @Context CategoryRepository categoryRepository, @Context UserAccountRepository userAccountRepository);

    @Named("mapCategoryNameToCategory")
    default Category mapCategoryNameToCategory(String categoryName, @Context CategoryRepository categoryRepository) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryName));
    }

    @Named("mapIdsToUserAccounts")
    default Set<UserAccount> mapIdsToUserAccounts(Set<Long> ids, @Context UserAccountRepository userAccountRepository) {
        return ids.stream()
                .map(id -> userAccountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("UserAccount not found: " + id)))
                .collect(Collectors.toSet());
    }

    @Named("mapUserAccountsToIds")
    default Set<Long> mapUserAccountsToIds(Set<UserAccount> userAccounts) {
        return userAccounts.stream()
                .map(UserAccount::getId)
                .collect(Collectors.toSet());
    }
}
