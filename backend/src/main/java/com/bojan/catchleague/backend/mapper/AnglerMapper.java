package com.bojan.catchleague.backend.mapper;

import com.bojan.catchleague.backend.dto.AnglerDto;
import com.bojan.catchleague.backend.model.Angler;

public final class AnglerMapper {
    private AnglerMapper(){}

    public static AnglerDto toDto(Angler a){
        return new AnglerDto(a.getId(), a.getName(), a.getEmail());
    }
}
