package com.delani.homegrid.dtos.responses;

import com.delani.homegrid.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
