package com.example.storyeverything.rest;

import com.example.storyeverything.dto.InformationDTO;
import com.example.storyeverything.service.InformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InformationRestController {
    private final InformationService informationService;

    public InformationRestController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/information")
    public ResponseEntity<List<InformationDTO>> findAll(){
        List<InformationDTO> information = informationService.findAll();
        return ResponseEntity.ok(information);
    }

    @GetMapping("/information/users/{id}")
    public ResponseEntity<List<InformationDTO>> findByUserId(@PathVariable long id){
        List<InformationDTO> information = informationService.findByAccountUserId(id);
        return ResponseEntity.ok(information);
    }

    @GetMapping("/information/category/{id}")
    public ResponseEntity<List<InformationDTO>> findByCategoryId(@PathVariable long id){
        List<InformationDTO> information = informationService.findByCategoryId(id);
        return ResponseEntity.ok(information);
    }

    @GetMapping("/information/{id}")
    public ResponseEntity<InformationDTO> findById(@PathVariable long id){
        InformationDTO information = informationService.findById(id);
        return ResponseEntity.ok(information);
    }

    @PostMapping("/information")
    public ResponseEntity<InformationDTO> add(@RequestBody InformationDTO informationDTO){
        InformationDTO newInformation = informationService.create(informationDTO);
        return new ResponseEntity<>(newInformation, HttpStatus.CREATED);
    }

    @PutMapping("/information/{id}")
    public ResponseEntity<InformationDTO> update(@PathVariable long id, @RequestBody InformationDTO informationDTO){
        InformationDTO updatedInformation = informationService.update(id, informationDTO);
        return ResponseEntity.ok(updatedInformation);
    }

    @DeleteMapping("/information/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        if(informationService.findById(id) == null)
            throw new RuntimeException("Information id not found - " + id);

        informationService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
