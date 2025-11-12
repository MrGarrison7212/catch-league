package com.bojan.catchleague.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class AnglerUpdateDto {

    @NotBlank(message = "name must not be blank")
    private String name;

    public AnglerUpdateDto() {}

    public AnglerUpdateDto(String name) { this.name = name; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

}
