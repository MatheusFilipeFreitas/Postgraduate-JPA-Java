package com.mathffreitas.jpaunipds.mapper.user;

import com.mathffreitas.jpaunipds.mapper.common.CreateDtoToEntityMapper;
import com.mathffreitas.jpaunipds.mapper.common.EntityToDtoMapper;
import com.mathffreitas.jpaunipds.mapper.common.UpdateDtoToEntityMapper;
import com.mathffreitas.jpaunipds.model.dto.user.UserCreateDto;
import com.mathffreitas.jpaunipds.model.dto.user.UserDto;
import com.mathffreitas.jpaunipds.model.dto.user.UserUpdateDto;
import com.mathffreitas.jpaunipds.model.entity.user.User;

public interface UserMapper extends
        EntityToDtoMapper<User, UserDto>,
        CreateDtoToEntityMapper<UserCreateDto, User>,
        UpdateDtoToEntityMapper<UserUpdateDto, User> {
}
