package ru.smal.tasklist.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.smal.tasklist.domain.user.User;
import ru.smal.tasklist.service.AuthService;
import ru.smal.tasklist.service.UserService;
import ru.smal.tasklist.web.dto.auth.JwtRequest;
import ru.smal.tasklist.web.dto.auth.JwtResponse;
import ru.smal.tasklist.web.dto.user.UserDto;
import ru.smal.tasklist.web.dto.validation.OnCreate;
import ru.smal.tasklist.web.mapper.UserMapper;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody JwtRequest loginRequest) {
        log.info("request for login");
        return authService.login(loginRequest);
    }

    @PostMapping
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User created = userService.create(user);
        return userMapper.toDto(created);
    }

    @PostMapping("refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
