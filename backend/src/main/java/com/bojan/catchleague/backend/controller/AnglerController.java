package com.bojan.catchleague.backend.controller;

import com.bojan.catchleague.backend.model.Angler;
import com.bojan.catchleague.backend.repository.AnglerRepository;
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
}
