package com.ql.base_java.controller;

import com.ql.base_java.model.dto.UserDto;
import com.ql.base_java.payloads.ApiResponse;
import com.ql.base_java.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody @Valid UserDto userDto) {
        String response = authService.registerUser(userDto);
        return new ResponseEntity<>(ApiResponse.builder().status(true).data(response).build(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<UserDto> users = authService.getAllUsers();
        return new ResponseEntity<>(ApiResponse.builder().status(true).data(users).code(200).build(), HttpStatus.OK);
    }

}
