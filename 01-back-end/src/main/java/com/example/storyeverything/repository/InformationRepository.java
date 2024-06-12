package com.example.storyeverything.repository;

import com.example.storyeverything.model.Information;
import com.example.storyeverything.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
    List<Information>  findAllByUserAccountsContains(UserAccount userAccount);
    Page<Information>  findAllByUserAccountsContains(UserAccount userAccount, Pageable pageable);
}
