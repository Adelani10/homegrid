package com.delani.homegrid.dtos.requests;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SignupRequest {

    @NotEmpty(message = "Email cannot be empty")
    @NotNull(message = "Email cannot be null")
    @Column(unique = true)
    @Email(message = "Email is not properly formatted")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Passwords must be at least 8 chars long")
    private String password;

    @NotEmpty(message = "FirstName cannot be empty")
    @NotNull(message = "FirstName cannot be null")
    private String firstname;

    @NotEmpty(message = "LastName cannot be empty")
    @NotNull(message = "LastName cannot be null")
    private String lastname;

    @NotNull(message = "DOB cannot be null")
    private LocalDate dateOfBirth;
}
