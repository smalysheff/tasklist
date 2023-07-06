package ru.smal.tasklist.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request for login")
public class JwtRequest {

    @Schema(description = "email", example = "gosling@mail.ru")
    @NotNull(message = "Username cannot be empty")
    private String username;

    @Schema(description = "password", example = "java")
    @NotNull(message = "Password cannot be empty")
    private String password;
}
