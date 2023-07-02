package ru.smal.tasklist.service.impl;

import org.springframework.stereotype.Service;
import ru.smal.tasklist.service.AuthService;
import ru.smal.tasklist.web.dto.auth.JwtRequest;
import ru.smal.tasklist.web.dto.auth.JwtResponse;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
