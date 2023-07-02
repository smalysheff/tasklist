package ru.smal.tasklist.service;

import ru.smal.tasklist.web.dto.auth.JwtRequest;
import ru.smal.tasklist.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
