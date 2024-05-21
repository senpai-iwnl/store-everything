package com.example.storyeverything.rest;

import com.example.storyeverything.dto.UserAccountDTO;
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

    @GetMapping("/admin/users")
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
    public ResponseEntity<UserAccountDTO> addAsUser(@Valid @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO newUserAccount = userAccountService.createAsUser(userAccountDTO);

        return new ResponseEntity<>(newUserAccount, HttpStatus.CREATED);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<UserAccountDTO> addAsAdmin(@Valid @RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO newUserAccount = userAccountService.createAsAdmin(userAccountDTO);

        return new ResponseEntity<>(newUserAccount, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserAccountDTO> updateAsUser(@PathVariable long id,@Valid @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO updatedUserAccount = userAccountService.updateAsUser(id, userAccountDTO);

        return ResponseEntity.ok(updatedUserAccount);
    }

    @PutMapping("/admin/users/{id}")
    public ResponseEntity<UserAccountDTO> updateRole(@PathVariable long id, @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO updatedUser = userAccountService.updateAsAdmin(id, userAccountDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userAccountService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
