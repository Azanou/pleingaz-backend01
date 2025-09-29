package com.monpleingaz.centralpleingaz01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monpleingaz.centralpleingaz01.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping("/user")
    public ResponseEntity<String> syncUser(Authentication authentication) {
        Jwt jwt = (Jwt)authentication.getPrincipal();

        String uiid = jwt.getSubject();
        authService.synchronizeEmployer(uiid, jwt.getClaims());
        return ResponseEntity.ok("utilisateur synchroniser avec succes");
    }
    

}
