package com.mathffreitas.jpaunipds.mapper.user;

import com.mathffreitas.jpaunipds.model.dto.user.UserCreateDto;
import com.mathffreitas.jpaunipds.model.dto.user.UserDto;
import com.mathffreitas.jpaunipds.model.dto.user.UserUpdateDto;
import com.mathffreitas.jpaunipds.model.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    @Override
    public User fromCreateDto(UserCreateDto dto) {
        if (dto == null) {
            return null;
        }
        User entity = new User();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    @Override
    public User fromUpdateDto(UserUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        User entity = new User();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
