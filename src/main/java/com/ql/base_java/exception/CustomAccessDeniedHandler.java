package com.ql.base_java.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ql.base_java.payloads.ApiResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        ApiResponse apiResponse = ApiResponse.builder().status(false).code(403).build();
        new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
    }
}
