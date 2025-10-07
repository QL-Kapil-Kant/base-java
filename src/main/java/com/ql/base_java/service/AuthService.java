package com.ql.base_java.service;

import com.ql.base_java.payloads.LoginRequest;
import com.ql.base_java.model.dto.UserDto;
import com.ql.base_java.payloads.LoginResponse;

import java.util.List;

public interface AuthService {

    void registerUser(UserDto userDto);

    List<UserDto> getAllUsers();

    LoginResponse loginUser(LoginRequest loginRequest);

}
