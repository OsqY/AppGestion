package com.oscar.appgestion.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api")
public class ProtectedResourcesController {

    @GetMapping("/protected")
    public ResponseEntity<String> verifyToken() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null) {
            return ResponseEntity.ok("Valid token!");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non valid token!");

        }
    }
}
