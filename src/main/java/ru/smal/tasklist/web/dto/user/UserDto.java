package ru.smal.tasklist.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.smal.tasklist.web.dto.validation.OnCreate;
import ru.smal.tasklist.web.dto.validation.OnUpdate;

@Data
@Schema(name = "User DTO")
public class UserDto {

    @Schema(description = "User id", example = "1")
    @NotNull(message = "Id cannot be empty", groups = OnUpdate.class)
    private Long id;

    @Schema(description = "User name", example = "John")
    @NotNull(message = "Name cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Name cannot be longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Schema(description = "User username", example = "john@gmail.com")
    @NotNull(message = "Username cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Username cannot be longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @Schema(description = "User password", example = "1234")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @Schema(description = "User password confirmation", example = "1234")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation cannot be empty", groups = OnCreate.class)
    private String passwordConfirmation;
}
