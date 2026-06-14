package com.mathffreitas.jpaunipds.service.auth;

import com.mathffreitas.jpaunipds.exceptions.EmailAlreadyExistsException;
import com.mathffreitas.jpaunipds.exceptions.InvalidCredentialsException;
import com.mathffreitas.jpaunipds.exceptions.NotFoundException;
import com.mathffreitas.jpaunipds.model.dto.auth.RegisterRequest;
import com.mathffreitas.jpaunipds.model.entity.user.User;
import com.mathffreitas.jpaunipds.security.utils.token.BearerToken;
import com.mathffreitas.jpaunipds.security.utils.token.TokenUtils;
import com.mathffreitas.jpaunipds.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;

    @Override
    @Transactional
    public User register(RegisterRequest request) {
        validateRegisterRequest(request);

        if (userService.existsByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User user = new User(request.getName(), request.getEmail(), request.getPassword());
        return userService.create(user);
    }

    @Override
    public BearerToken login(String username, String password) {
        User user = userService.existsByEmail(username)
                .orElseThrow(() -> new NotFoundException(User.class.getName(), username));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials! Check your email or password information!");
        }
        return TokenUtils.enconde(user);
    }

    private void validateRegisterRequest(RegisterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Registration payload is required");
        }
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
    }
}
