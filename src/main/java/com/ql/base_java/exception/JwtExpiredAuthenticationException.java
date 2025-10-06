package com.ql.base_java.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtExpiredAuthenticationException extends AuthenticationException {
    public JwtExpiredAuthenticationException(String msg) {
        super(msg);
    }
}
