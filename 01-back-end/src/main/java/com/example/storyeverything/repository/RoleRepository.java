package com.example.storyeverything.repository;

import com.example.storyeverything.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
<<<<<<< HEAD
=======
    Optional<Role> findByRole(String role);
>>>>>>> 6f3763a0ef61fd30eacb6d3fcb695bef2b8d9ec4
}
