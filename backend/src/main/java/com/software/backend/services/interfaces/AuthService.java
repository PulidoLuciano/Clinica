package com.software.backend.services.interfaces;

import com.software.backend.controllers.dtos.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDto);
}
