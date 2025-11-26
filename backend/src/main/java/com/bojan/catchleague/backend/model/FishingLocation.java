package com.bojan.catchleague.backend.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "fishing_location")
public class FishingLocation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    public enum Type { RIVER, LAKE, POND, STREAM, CANAL }

    public FishingLocation() {}
    public FishingLocation(String name, Type type) {
        this.name = name;
        this.type = type;
    }


}
