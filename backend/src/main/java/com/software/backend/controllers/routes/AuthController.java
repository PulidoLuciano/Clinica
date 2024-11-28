package com.software.backend.controllers.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.software.backend.controllers.dtos.JwtAuthResponse;
import com.software.backend.controllers.dtos.LoginDTO;
import com.software.backend.services.interfaces.AuthService;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDto){
        String token = authService.login(loginDto.getEmail(), loginDto.getPassword());

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

}
