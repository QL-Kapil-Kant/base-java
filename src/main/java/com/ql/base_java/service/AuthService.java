package com.ql.base_java.service;

import com.ql.base_java.model.dto.LoginDto;
import com.ql.base_java.model.dto.UserDto;

import java.util.List;

public interface AuthService {

    String registerUser(UserDto userDto);

    List<UserDto> getAllUsers();

    String loginUser(LoginDto loginDto);

}
