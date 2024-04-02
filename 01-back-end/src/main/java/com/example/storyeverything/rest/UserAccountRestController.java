package com.example.storyeverything.rest;

import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.service.UserAccountService;
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
    public ResponseEntity<UserAccountDTO> add(@RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO newUserAccount = userAccountService.create(userAccountDTO);
        return new ResponseEntity<>(newUserAccount, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserAccountDTO> update(@PathVariable long id, @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO updatedUserAccount = userAccountService.update(id, userAccountDTO);
        return ResponseEntity.ok(updatedUserAccount);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        if(userAccountService.findById(id) == null)
            throw new RuntimeException("User id not found - " + id);

        userAccountService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
