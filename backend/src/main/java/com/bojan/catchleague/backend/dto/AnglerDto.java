package com.bojan.catchleague.backend.dto;

import java.time.Instant;

public record AnglerDto(Long id, String name, String email, Instant createdAt, Instant updatedAt) {}
