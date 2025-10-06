package com.ql.base_java.controller;

import com.ql.base_java.model.dto.LoginDto;
import com.ql.base_java.model.dto.UserDto;
import com.ql.base_java.payloads.ApiResponse;
import com.ql.base_java.service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody @Valid UserDto userDto) {
        String response = authService.registerUser(userDto);
        return new ResponseEntity<>(ApiResponse.builder().status(true).code(210).data(response).build(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers(@AuthenticationPrincipal UserDetails userDetails, Authentication authentication) {
        log.info("Authenticated user from userDetails: {}", authentication);
        log.info("Authenticated user from authentication: {}", userDetails);
        List<UserDto> users = authService.getAllUsers();
        return new ResponseEntity<>(ApiResponse.builder().status(true).code(211).data(users).build(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody @Valid LoginDto loginDto) {
        String jwtToken = authService.loginUser(loginDto);
        return new ResponseEntity<>(ApiResponse.builder().status(true).code(212).data(jwtToken).build(), HttpStatus.OK);
    }

}
