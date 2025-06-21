package com.delani.homegrid.dtos.responses;

import com.delani.homegrid.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private Long id;
    private Role role;
    private String token;
}
