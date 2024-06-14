package com.example.storyeverything.rest;

import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.security.jwt.JwtUtil;
import com.example.storyeverything.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "UserAccount", description = "User Account Management API")
public class UserAccountRestController {
    private final UserAccountService userAccountService;
    private final JwtUtil jwtUtil;

    public UserAccountRestController(UserAccountService userAccountService, JwtUtil jwtUtil) {
        this.userAccountService = userAccountService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/admin/users")
    @Operation(summary = "Get all users (Admin)", description = "Retrieve a list of all users (Admin access)")
    public ResponseEntity<List<UserAccountDTO>> findAll() {
        List<UserAccountDTO> userAccounts = userAccountService.findAll();
        return ResponseEntity.ok(userAccounts);
    }

    @PostMapping("/admin/users")
    @Operation(summary = "Add new user (Admin)", description = "Create a new user account (Admin access)")
    public ResponseEntity<UserAccountDTO> addAsAdmin(@Valid @RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO newUserAccount = userAccountService.createAsAdmin(userAccountDTO);
        return new ResponseEntity<>(newUserAccount, HttpStatus.CREATED);
    }

    @PutMapping("/admin/users/{id}")
    @Operation(summary = "Update user role (Admin)", description = "Update an existing user account role by ID (Admin access)")
    public ResponseEntity<UserAccountDTO> updateRole(@PathVariable long id,@Valid @RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO updatedUser = userAccountService.updateAsAdmin(id, userAccountDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/admin/users/{id}")
    @Operation(summary = "Delete user (Admin)", description = "Delete a user account by its ID (Admin access)")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userAccountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a user account by its ID")
    public ResponseEntity<UserAccountDTO> findById(@PathVariable long id) {
        UserAccountDTO userAccount = userAccountService.findById(id);
        return ResponseEntity.ok(userAccount);
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Update user (User)", description = "Update an existing user account by ID")
    public ResponseEntity<UserAccountDTO> updateAsUser(@PathVariable long id, @Valid @RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO updatedUserAccount = userAccountService.updateAsUser(id, userAccountDTO);
        return ResponseEntity.ok(updatedUserAccount);
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Create a new user account")
    public ResponseEntity<UserAccountDTO> addAsUser(@Valid @RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO newUserAccount = userAccountService.createAsUser(userAccountDTO);
        return new ResponseEntity<>(newUserAccount, HttpStatus.CREATED);
    }

    @GetMapping("/token")
    @Operation(summary = "Get user by token", description = "Retrieve a user account by JWT token")
    public ResponseEntity<UserAccountDTO> findByToken(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        String login = jwtUtil.extractLogin(jwtToken);
        UserAccountDTO user = userAccountService.findByToken(login);
        return ResponseEntity.ok(user);
    }
}
