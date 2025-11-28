package com.bojan.catchleague.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AnglerUpdateDto {

    @NotBlank(message = "name must not be blank")
    @Size(max = 100)
    private String name;
    @Email
    @Size(max = 255)
    private String email;

    public AnglerUpdateDto() {}

    public AnglerUpdateDto(String name) { this.name = name; }

    public String getName() { return this.name; }

    public String getEmail() { return this.email; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

}
