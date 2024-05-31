package com.example.storyeverything.rest;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.security.jwt.JwtUtil;
import com.example.storyeverything.service.InformationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/information")
public class InformationRestController {
    private final InformationService informationService;
    private final JwtUtil jwtUtil;

    public InformationRestController(InformationService informationService, JwtUtil jwtUtil) {
        this.informationService = informationService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping()
    public ResponseEntity<List<InformationDTO>> findAll(@RequestHeader("Authorization") String token){
        String jwtToken = token.substring(7);
        String login = jwtUtil.extractLogin(jwtToken);
        List<InformationDTO> information = informationService.findAllByLogin(login);
        return ResponseEntity.ok(information);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<InformationDTO>> findAllPaged(
            @RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractLogin(jwtToken);
        Page<InformationDTO> informationPage = informationService.findAllByLoginWithPagination(username, page, size, sortBy, direction);
        return ResponseEntity.ok(informationPage);
    }


    @GetMapping("/{id}")
    public ResponseEntity<InformationDTO> findById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractLogin(jwtToken);
        InformationDTO information = informationService.findByIdAndLogin(id, username);
        return ResponseEntity.ok(information);
    }

    @PostMapping
    public ResponseEntity<InformationDTO> add(@RequestBody InformationDTO informationDTO, @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractLogin(jwtToken);
        InformationDTO createdInformation = informationService.create(informationDTO, username);
        return ResponseEntity.ok(createdInformation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InformationDTO> updateInformation(@PathVariable Long id, @RequestBody InformationDTO informationDTO, @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractLogin(jwtToken);
        InformationDTO updatedInformation = informationService.update(id, informationDTO, username);
        return ResponseEntity.ok(updatedInformation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInformation(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractLogin(jwtToken);
        informationService.delete(id, username);
        return ResponseEntity.noContent().build();
    }
}
