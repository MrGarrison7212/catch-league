package com.bojan.catchleague.backend.controller;

import com.bojan.catchleague.backend.dto.AnglerDto;
import com.bojan.catchleague.backend.mapper.AnglerMapper;
import com.bojan.catchleague.backend.model.Angler;
import com.bojan.catchleague.backend.repository.AnglerRepository;
import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/anglers")
public class AnglerController {
    private final AnglerRepository repo;

    public AnglerController(AnglerRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<AnglerDto> all() {
        return repo.findAll().stream().map(AnglerMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public AnglerDto byID(@PathVariable Long id){
        Angler a = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Angler not found."));
        return AnglerMapper.toDto(a);
    }

    @PostMapping
    public ResponseEntity<Angler> create(@Valid @RequestBody com.bojan.catchleague.backend.dto.AnglerCreateDto dto) {
        Angler saved = repo.save(new Angler(dto.getName(), dto.getEmail()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Angler not found"
            );
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AnglerDto> patch(
        @PathVariable Long id,
        @Valid @RequestBody com.bojan.catchleague.backend.dto.AnglerUpdateDto dto) {

        Angler entity = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Angler not found."));

        if(dto.getName() != null){
            String newName = dto.getName().trim();
            if(newName.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name must not be blank");
            }
            entity.setName(newName);
        }
        if(dto.getEmail() != null){
            String newEmail = dto.getEmail().trim();
            if(newEmail.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email must not be blank");
            }
            if (!newEmail.contains("@")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email format is invalid.");
            }
            if (repo.existsByEmailAndIdNot(newEmail, id)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "email is already taken.");
            }
            entity.setEmail(newEmail);
        }
        Angler saved = repo.save(entity);

        return ResponseEntity.ok(AnglerMapper.toDto(saved));
    }


}
