package com.delani.homegrid.services.impl;

import com.delani.homegrid.dtos.requests.LoginRequest;
import com.delani.homegrid.dtos.requests.SignupRequest;
import com.delani.homegrid.dtos.responses.LoginResponse;
import com.delani.homegrid.dtos.responses.SignupResponse;
import com.delani.homegrid.entities.User;
import com.delani.homegrid.enums.Role;
import com.delani.homegrid.exceptions.EmailAlreadyExistsException;
import com.delani.homegrid.exceptions.InvalidLoginCredentialsException;
import com.delani.homegrid.payload.ApiResponse;
import com.delani.homegrid.payload.ApiResponseUtil;
import com.delani.homegrid.repos.UserRepository;
import com.delani.homegrid.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<ApiResponse<?>> signUp(SignupRequest signupRequest) {
        Optional<User> user = userRepository.findByEmail(signupRequest.getEmail());

        if(user.isPresent()) {
            throw new EmailAlreadyExistsException("Email already in use");
        }

        User _user = User.builder()
                .email(signupRequest.getEmail()).firstname(signupRequest.getFirstname())
                .password(encoder.encode(signupRequest.getPassword()))
                .lastname(signupRequest.getLastname()).role(Role.USER).dateOfBirth(signupRequest.getDateOfBirth())
                .isActive(true)
                .build();

        User savedUser = userRepository.save(_user);

        SignupResponse response = SignupResponse.builder()
                .id(savedUser.getId()).email(savedUser.getEmail())
                .firstName(savedUser.getFirstname())
                .lastName(savedUser.getLastname()).role(savedUser.getRole()).build();


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseUtil.buildSuccessResponse(response,"Account successfully created"));
    }

    public ResponseEntity<ApiResponse<?>> login(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseUtil.buildErrorResponse("User not found"));
        }

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()
                    )
            );

            User user = optionalUser.get();

            String token = jwtService.generateToken(user.getEmail());

            LoginResponse response = LoginResponse.builder()
                    .id(user.getId())
                    .token(token)
                    .role(user.getRole())
                    .build();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponseUtil.buildSuccessResponse(response, "Login successful"));

        } catch (BadCredentialsException e) {
            throw new InvalidLoginCredentialsException("Invalid email or password");
        }
    }

}
