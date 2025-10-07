package com.ql.base_java.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "must not be blank")
    @Size(min = 2, max = 50, message = "must be between 2 and 50 characters")
    @Email(message = "must be a valid email address")
    private String email;

    @NotBlank(message = "must not be blank")
    @Size(min = 8, max = 100, message = "must be between 8 and 100 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
            message = "must contain at least one lowercase letter, one uppercase letter, and one special character"
    )
    private String password;

}
