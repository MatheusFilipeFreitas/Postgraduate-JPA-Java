package com.mathffreitas.jpaunipds.repository.user;

import com.mathffreitas.jpaunipds.model.entity.user.User;
import com.mathffreitas.jpaunipds.repository.common.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findUsersByEmail(String email);
}
