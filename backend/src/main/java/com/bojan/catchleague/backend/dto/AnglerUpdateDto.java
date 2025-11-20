package com.bojan.catchleague.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class AnglerUpdateDto {

    @NotBlank(message = "name must not be blank")
    private String name;
    private String email;

    public AnglerUpdateDto() {}

    public AnglerUpdateDto(String name) { this.name = name; }

    public String getName() { return this.name; }

    public String getEmail() { return this.email; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

}
