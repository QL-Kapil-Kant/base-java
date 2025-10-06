package com.ql.base_java.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ql.base_java.exception.JwtExpiredAuthenticationException;
import com.ql.base_java.payloads.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        int errorCode = HttpServletResponse.SC_FORBIDDEN;
        String message = "Unauthorized";

        if (authException instanceof JwtExpiredAuthenticationException) {
            errorCode = HttpServletResponse.SC_UNAUTHORIZED;
            message = "Token expired";
        } else if (authException instanceof UsernameNotFoundException) {
            errorCode = HttpServletResponse.SC_UNAUTHORIZED;
            message = "Username not found";
        } else if (authException instanceof InsufficientAuthenticationException) {
            errorCode = HttpServletResponse.SC_UNAUTHORIZED;
            message = "Token invalid";
        } else if (authException instanceof BadCredentialsException) {
            errorCode = HttpServletResponse.SC_UNAUTHORIZED;
            message = "Bad credentials";
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorCode);
        ApiResponse apiResponse = ApiResponse.builder().status(false).code(errorCode).message(message).build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), apiResponse);
    }

}