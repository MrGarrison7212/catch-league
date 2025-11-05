package com.bojan.catchleague.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class AnglerCreateDto {

    @NotBlank(message = "name must not be blank")
    private String name;

    public String getName() {  return name; }
    public void setName(String name) { this.name = name; }

}
