package com.mathffreitas.jpaunipds.service.user;

import com.mathffreitas.jpaunipds.model.entity.user.User;
import com.mathffreitas.jpaunipds.repository.common.BaseRepository;
import com.mathffreitas.jpaunipds.repository.user.UserRepository;
import com.mathffreitas.jpaunipds.service.common.AbstractBaseService;
import org.springframework.stereotype.Service;

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
    protected String getEntityName() {
        return "User";
    }

    @Override
    protected void updateEntity(User existing, User incoming) {
        existing.setName(incoming.getName());
        existing.setEmail(incoming.getEmail());
    }
}
