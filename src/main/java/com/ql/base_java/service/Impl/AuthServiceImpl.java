package com.ql.base_java.service.Impl;

import com.ql.base_java.jwt.JwtUtil;
import com.ql.base_java.model.User;
import com.ql.base_java.model.dto.LoginDto;
import com.ql.base_java.model.dto.UserDto;
import com.ql.base_java.repository.UserRepository;
import com.ql.base_java.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String registerUser(UserDto userDto) {
        try {
            User user = modelMapper.map(userDto, User.class);

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            log.info("Mapped user is: {}", user);

            userRepository.save(user);
            return "User registered successfully";
        } catch (Exception e) {
            log.info("Error while registering user: {}", e.getMessage());
            throw new RuntimeException("Error while registering user" + e.getMessage());
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
        } catch (Exception e) {
            log.info("Error while fetching all users: {}", e.getMessage());
            throw new RuntimeException("Error while fetching all users" + e.getMessage());
        }
    }

    @Override
    public String loginUser(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
            return jwtUtil.generateToken(loginDto.getUserName());
        } catch (BadCredentialsException ex) {
            throw ex;
        } catch (Exception e) {
            log.info("Error during authentication: {} and error name: {}", e.getMessage(), e.getClass());
            throw new RuntimeException("Error during authentication: " + e.getMessage());
        }
    }
}
