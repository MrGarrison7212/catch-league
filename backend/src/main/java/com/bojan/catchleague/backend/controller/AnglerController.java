package com.bojan.catchleague.backend.controller;

import com.bojan.catchleague.backend.model.Angler;
import com.bojan.catchleague.backend.repository.AnglerRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anglers")
public class AnglerController {
    private final AnglerRepository repo;

    public AnglerController(AnglerRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Angler> all() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Angler> create(@Valid @RequestBody com.bojan.catchleague.backend.dto.AnglerCreateDto dto) {
        Angler saved = repo.save(new Angler(dto.getName()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
