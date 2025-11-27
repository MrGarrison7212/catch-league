package com.bojan.catchleague.backend.mapper;

import com.bojan.catchleague.backend.dto.FishingLocationDto;
import com.bojan.catchleague.backend.model.FishingLocation;

import java.time.format.DateTimeFormatter;

public final class FishingLocationMapper {
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_INSTANT;

    public static FishingLocationDto toDto(FishingLocation f) {
        return new FishingLocationDto(
                f.getId(),
                f.getName(),
                f.getType().name(),
                ISO.format(f.getCreatedAt()),
                ISO.format(f.getUpdatedAt())
        );
    }
}
