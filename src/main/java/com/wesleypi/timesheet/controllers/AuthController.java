package com.wesleypi.timesheet.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/token")
    Object postToken(Object authentication);

    @PostMapping("/refresh-token")
    Object postRefreshToken(Object authentication);

    @PostMapping("/recover-password")
    Object postRecoverPassword(Object authentication);

    @PutMapping("/recover-password")
    Object putRecoverPassword(Object authentication);
}
