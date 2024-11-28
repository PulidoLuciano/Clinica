package com.software.backend.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.software.backend.persistence.repositories.interfaces.UsuariosRepository;
import com.software.backend.security.JwtTokenProvider;
import com.software.backend.services.interfaces.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public String login(String email, String password) {

        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(upat);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication, usuariosRepository.findById(email).get());

        return token;
    }
}