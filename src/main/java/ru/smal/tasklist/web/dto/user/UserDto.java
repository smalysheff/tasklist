package ru.smal.tasklist.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.smal.tasklist.web.dto.validation.OnCreate;
import ru.smal.tasklist.web.dto.validation.OnUpdate;

@Data
public class UserDto {

    @NotNull(message = "Id cannot be empty", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Name cannot be longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(message = "Username cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Username cannot be longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation cannot be empty", groups = OnCreate.class)
    private String passwordConfirmation;
}
