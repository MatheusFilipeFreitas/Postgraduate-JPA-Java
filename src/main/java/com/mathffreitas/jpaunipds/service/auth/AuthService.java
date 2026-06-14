package com.mathffreitas.jpaunipds.service.auth;

import com.mathffreitas.jpaunipds.model.dto.auth.RegisterRequest;
import com.mathffreitas.jpaunipds.model.entity.user.User;
import com.mathffreitas.jpaunipds.security.utils.token.BearerToken;

public interface AuthService {

    User register(RegisterRequest request);
    BearerToken login(String username, String password);
}
