package com.bojan.catchleague.backend.mapper;

import com.bojan.catchleague.backend.dto.AnglerDto;
import com.bojan.catchleague.backend.model.Angler;

import java.time.format.DateTimeFormatter;

public final class AnglerMapper {
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_INSTANT;
    private AnglerMapper(){}

    public static AnglerDto toDto(Angler a){
        return new AnglerDto(
                a.getId(),
                a.getName(),
                a.getEmail(),
                ISO.format(a.getCreatedAt()),
                ISO.format(a.getUpdatedAt()));
    }
}
