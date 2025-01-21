package com.example.SongLingo.account.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoginRequest {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;
}
