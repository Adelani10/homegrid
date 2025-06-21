package com.delani.homegrid.entities;


import com.delani.homegrid.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "_user")
public class User extends BaseEntity {

    private String password;

    @JsonProperty("email")
    @Column(unique = true, nullable = false)
    private String email;

    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonProperty("isActive")
    private boolean isActive;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Apartment> apartments;
}
