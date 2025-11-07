package com.bojan.catchleague.backend.controller;

import com.bojan.catchleague.backend.dto.AnglerDto;
import com.bojan.catchleague.backend.mapper.AnglerMapper;
import com.bojan.catchleague.backend.model.Angler;
import com.bojan.catchleague.backend.repository.AnglerRepository;
import jakarta.validation.Valid;
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
        Angler saved = repo.save(new Angler(dto.getName()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
