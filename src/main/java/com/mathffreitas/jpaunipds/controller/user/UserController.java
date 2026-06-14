package com.mathffreitas.jpaunipds.controller.user;

import com.mathffreitas.jpaunipds.controller.common.BaseController;
import com.mathffreitas.jpaunipds.hateoas.UserResourceAssembler;
import com.mathffreitas.jpaunipds.mapper.user.UserMapper;
import com.mathffreitas.jpaunipds.model.dto.user.UserCreateDto;
import com.mathffreitas.jpaunipds.model.dto.user.UserDto;
import com.mathffreitas.jpaunipds.model.dto.user.UserUpdateDto;
import com.mathffreitas.jpaunipds.model.entity.user.User;
import com.mathffreitas.jpaunipds.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "User management endpoints")
public class UserController extends BaseController<
        User, UserDto, UserCreateDto, UserUpdateDto, UserService, UserMapper, UserResourceAssembler> {

    public UserController(UserService userService, UserMapper userMapper, UserResourceAssembler assembler) {
        super(userService, userMapper, assembler);
    }
}
