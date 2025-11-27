package com.bojan.catchleague.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FishingLocationCreateDto {
    @NotBlank
    private String name;
    @NotNull
    private String type;

    public String getName() { return name; }
    public String getType() { return type; }
}
