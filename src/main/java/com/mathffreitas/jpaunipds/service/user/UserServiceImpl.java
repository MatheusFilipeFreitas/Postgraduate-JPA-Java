package com.mathffreitas.jpaunipds.service.user;

import com.mathffreitas.jpaunipds.model.entity.user.User;
import com.mathffreitas.jpaunipds.repository.common.BaseRepository;
import com.mathffreitas.jpaunipds.repository.user.UserRepository;
import com.mathffreitas.jpaunipds.service.common.AbstractBaseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractBaseService<User> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected BaseRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    public User create(User user) {
        if (existsByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }
        return userRepository.save(user);
    }

    @Override
    protected String getEntityName() {
        return "User";
    }

    @Override
    protected void updateEntity(User existing, User incoming) {
        existing.setName(incoming.getName());
        existing.setEmail(incoming.getEmail());
    }

    @Override
    public Optional<User> existsByEmail(String email) {
        return userRepository.findUsersByEmail(email);
    }
}
