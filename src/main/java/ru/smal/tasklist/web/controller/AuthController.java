package ru.smal.tasklist.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        log.info("request for login");
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
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
