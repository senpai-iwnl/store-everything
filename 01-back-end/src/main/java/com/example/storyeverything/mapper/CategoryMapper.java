package com.example.storyeverything.mapper;

import com.example.storyeverything.dto.CategoryDTO;
import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.model.Category;
import com.example.storyeverything.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDTO toDTO(Category category);
    List<CategoryDTO> toDTOList(List<Category> categories);
    Category toEntity(CategoryDTO categoryDTO);
    @Mapping(target = "id", ignore = true)
    void updateCategoryFromDTO(CategoryDTO categoryDTO, @MappingTarget Category category);
}
