package com.example.storyeverything.rest;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.dto.UserAccountDTO;
import com.example.storyeverything.security.jwt.JwtUtil;
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
    private final JwtUtil jwtUtil;

    public UserAccountRestController(UserAccountService userAccountService, JwtUtil jwtUtil) {
        this.userAccountService = userAccountService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<UserAccountDTO>> findAll(){
        List<UserAccountDTO> userAccounts = userAccountService.findAll();

        return ResponseEntity.ok(userAccounts);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<UserAccountDTO> addAsAdmin(@Valid @RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO newUserAccount = userAccountService.createAsAdmin(userAccountDTO);

        return new ResponseEntity<>(newUserAccount, HttpStatus.CREATED);
    }

    @PutMapping("/admin/users/{id}")
    public ResponseEntity<UserAccountDTO> updateRole(@PathVariable long id, @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO updatedUser = userAccountService.updateAsAdmin(id, userAccountDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("admin/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userAccountService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserAccountDTO> findById(@PathVariable long id){
        UserAccountDTO userAccount = userAccountService.findById(id);

        return ResponseEntity.ok(userAccount);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserAccountDTO> updateAsUser(@PathVariable long id,@Valid @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO updatedUserAccount = userAccountService.updateAsUser(id, userAccountDTO);

        return ResponseEntity.ok(updatedUserAccount);
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccountDTO> addAsUser(@Valid @RequestBody UserAccountDTO userAccountDTO){
        UserAccountDTO newUserAccount = userAccountService.createAsUser(userAccountDTO);

        return new ResponseEntity<>(newUserAccount, HttpStatus.CREATED);
    }

    @GetMapping("/users/token")
    public ResponseEntity<UserAccountDTO> findByToken(@RequestHeader("Authorization") String token){
        String jwtToken = token.substring(7);
        String login = jwtUtil.extractLogin(jwtToken);
        UserAccountDTO user = userAccountService.findByToken(login);
        return ResponseEntity.ok(user);
    }
}
