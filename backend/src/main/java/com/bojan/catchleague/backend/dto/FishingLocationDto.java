package com.bojan.catchleague.backend.dto;

import java.time.Instant;

public record FishingLocationDto(Long id, String name, String type, Instant createdAt, Instant updatedAt) {}