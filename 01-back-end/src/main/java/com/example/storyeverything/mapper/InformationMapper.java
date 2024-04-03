package com.example.storyeverything.mapper;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.exception.FieldNotFoundException;
import com.example.storyeverything.model.Category;
import com.example.storyeverything.model.Information;
import com.example.storyeverything.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class InformationMapper {

    @Autowired
    protected CategoryRepository categoryRepository;

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "userAccount.id", target = "userAccountId")
    public abstract InformationDTO toDTO(Information information);
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "userAccount.id", target = "userAccountId")
    public abstract List<InformationDTO> toDTOList(List<Information> information);

    @Mapping(target = "category", source = "categoryName")
    @Mapping(source = "userAccountId", target = "userAccount.id")
    public abstract Information toEntity(InformationDTO informationDTO);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addDate", ignore = true)
    public abstract void updateInformationFromDTO(InformationDTO dto, @MappingTarget Information information);

    // Method to resolve a Category entity from a category name
    protected Category categoryFromName(String name) {
        if (name == null) {
            return null;
        }
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new FieldNotFoundException("Category", "name", name));
    }

    // Method to extract category name from a Category entity
    protected String nameFromCategory(Category category) {
        return category == null ? null : category.getName();
    }
}
