package com.example.storyeverything.mapper;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.model.Category;
import com.example.storyeverything.model.Information;
import com.example.storyeverything.repository.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InformationMapper {

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "userAccount.id", target = "userAccountId")
    InformationDTO toDTO(Information information);

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "userAccount.id", target = "userAccountId")
    List<InformationDTO> toDTOList(List<Information> information);

    @Mapping(target = "category", source = "categoryName", qualifiedByName = "mapCategoryNameToCategory")
    @Mapping(source = "userAccountId", target = "userAccount.id")
    Information toEntity(InformationDTO informationDTO, @Context CategoryRepository categoryRepository);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addDate", ignore = true)
    @Mapping(target = "category", expression = "java(mapCategoryNameToCategory(dto.getCategoryName(), categoryRepository))")
    void updateInformationFromDTO(InformationDTO dto, @MappingTarget Information information, @Context CategoryRepository categoryRepository);

    @Named("mapCategoryNameToCategory")
    default Category mapCategoryNameToCategory(String categoryName, @Context CategoryRepository categoryRepository) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryName));
    }

    default String mapCategoryToCategoryName(Category category) {
        return category.getName();
    }
}
