package com.bojan.catchleague.backend.controller;

import com.bojan.catchleague.backend.dto.FishingLocationCreateDto;
import com.bojan.catchleague.backend.dto.FishingLocationDto;
import com.bojan.catchleague.backend.mapper.FishingLocationMapper;
import com.bojan.catchleague.backend.model.FishingLocation;
import com.bojan.catchleague.backend.repository.FishingLocationRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class FishingLocationController {
    private final FishingLocationRepository repo;

    public FishingLocationController(FishingLocationRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<FishingLocationDto> all() {
        return repo.findAll().stream().map(FishingLocationMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public FishingLocationDto byId(@PathVariable Long id){
        var f = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found"));
        return FishingLocationMapper.toDto(f);
    }

    @PostMapping
    public ResponseEntity<FishingLocationDto> create(@Valid @RequestBody FishingLocationCreateDto dto) {
        var entity = new FishingLocation(dto.getName(), FishingLocation.Type.valueOf(dto.getType()));
        var saved = repo.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(FishingLocationMapper.toDto(saved));
    }

}
