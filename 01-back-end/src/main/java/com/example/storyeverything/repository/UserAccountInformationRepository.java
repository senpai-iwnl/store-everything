package com.example.storyeverything.repository;

import com.example.storyeverything.model.Information;
import com.example.storyeverything.model.UserAccount;
import com.example.storyeverything.model.UserAccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountInformationRepository extends JpaRepository<UserAccountInformation, Long> {
    Optional<UserAccountInformation> findByUserAccountAndInformation(UserAccount userAccount, Information information);
    List<UserAccountInformation> findByInformation(Information information);
    void deleteByUserAccountAndInformation(UserAccount userAccount, Information information);
}
