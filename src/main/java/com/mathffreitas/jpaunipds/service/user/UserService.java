package com.mathffreitas.jpaunipds.service.user;

import com.mathffreitas.jpaunipds.model.entity.user.User;
import com.mathffreitas.jpaunipds.service.common.BaseService;

import java.util.Optional;

public interface UserService extends BaseService<User> {

    Optional<User> existsByEmail(String email);
}
