package com.example.storyeverything.repository;

import com.example.storyeverything.model.Information;
import com.example.storyeverything.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
    List<Information> findAllByUserAccountId(Long id);
    List<Information> findAllByCategoryId(Long id);
}
