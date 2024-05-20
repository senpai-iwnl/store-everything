package com.example.storyeverything.rest;

import com.example.storyeverything.dto.UserAccountDTO;
<<<<<<< HEAD
=======
import com.example.storyeverything.dto.UserRoleUpdateDTO;
>>>>>>> 6f3763a0ef61fd30eacb6d3fcb695bef2b8d9ec4
import com.example.storyeverything.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAccountRestController {
    private final UserAccountService userAccountService;

    public UserAccountRestController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserAccountDTO>> findAll(){
        List<UserAccountDTO> userAccounts = userAccountService.findAll();

        return ResponseEntity.ok(userAccounts);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserAccountDTO> findById(@PathVariable long id){
        UserAccountDTO userAccount = userAccountService.findById(id);

        return ResponseEntity.ok(userAccount);
    }

    @PostMapping("/users")
<<<<<<< HEAD
    public ResponseEntity<UserAccountDTO> addAsUser(@Valid @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO newUserAccount = userAccountService.createAsUser(userAccountDTO);

        return new ResponseEntity<>(newUserAccount, HttpStatus.CREATED);
    }

    @PostMapping("/users/admin")
    public ResponseEntity<UserAccountDTO> addAsAdmin(@Valid @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO newUserAccount = userAccountService.createAsAdmin(userAccountDTO);
=======
    public ResponseEntity<UserAccountDTO> add(@Valid @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO newUserAccount = userAccountService.create(userAccountDTO);
>>>>>>> 6f3763a0ef61fd30eacb6d3fcb695bef2b8d9ec4

        return new ResponseEntity<>(newUserAccount, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
<<<<<<< HEAD
    public ResponseEntity<UserAccountDTO> updateAsUser(@PathVariable long id,@Valid @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO updatedUserAccount = userAccountService.updateAsUser(id, userAccountDTO);
=======
    public ResponseEntity<UserAccountDTO> update(@PathVariable long id,@Valid @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO updatedUserAccount = userAccountService.update(id, userAccountDTO);
>>>>>>> 6f3763a0ef61fd30eacb6d3fcb695bef2b8d9ec4

        return ResponseEntity.ok(updatedUserAccount);
    }

<<<<<<< HEAD
    @PutMapping("/users/{id}/admin")
    public ResponseEntity<UserAccountDTO> updateRole(@PathVariable long id, @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO updatedUser = userAccountService.updateAsAdmin(id, userAccountDTO);
=======
    @PutMapping("/users/{id}/role")
    public ResponseEntity<UserAccountDTO> updateRole(@PathVariable long id, @RequestBody UserRoleUpdateDTO userRoleUpdateDTO){
        UserAccountDTO updatedUser = userAccountService.updateRole(id, userRoleUpdateDTO.getRole());
>>>>>>> 6f3763a0ef61fd30eacb6d3fcb695bef2b8d9ec4

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userAccountService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
