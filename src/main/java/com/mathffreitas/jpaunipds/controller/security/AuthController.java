package com.mathffreitas.jpaunipds.controller.security;

import com.mathffreitas.jpaunipds.mapper.user.UserMapper;
import com.mathffreitas.jpaunipds.model.dto.auth.LoginRequest;
import com.mathffreitas.jpaunipds.model.dto.auth.RegisterRequest;
import com.mathffreitas.jpaunipds.model.dto.user.UserDto;
import com.mathffreitas.jpaunipds.security.utils.token.BearerToken;
import com.mathffreitas.jpaunipds.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication endpoints")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<BearerToken> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(authService.login(request.getEmail(), request.getPassword()));
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toDto(authService.register(request)));
    }
}
