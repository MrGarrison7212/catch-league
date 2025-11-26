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

    @PrePersist
    void prePersist() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Type getType() {return type; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setName(String name) { this.name = name; }
    public void setType(Type type) { this.type = type; }
}
