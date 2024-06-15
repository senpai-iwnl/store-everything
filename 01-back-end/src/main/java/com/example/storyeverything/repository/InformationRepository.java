package com.example.storyeverything.repository;

import com.example.storyeverything.model.Information;
import com.example.storyeverything.model.UserAccount;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
    List<Information> findAllByUserAccountId(Long id);
    Optional<Information> findByIdAndUserAccountId(Long id, Long userAccountId);
    Optional<Information> findById(Long id);
    List<Information> findAllByIsPublic(Boolean isPublic);
    Page<Information> findAllByUserAccountId(Long userAccountId, Pageable pageable);

}
