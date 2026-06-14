package com.mathffreitas.jpaunipds.hateoas;

import com.mathffreitas.jpaunipds.controller.user.UserController;
import com.mathffreitas.jpaunipds.model.dto.user.UserDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserResourceAssembler implements RepresentationModelAssembler<UserDto, UserDto> {

    @Override
    @NonNull
    public UserDto toModel(@NonNull UserDto dto) {
        dto.add(createSelfLink(dto.getId()));
        dto.add(linkTo(methodOn(UserController.class).findAll(null, null)).withRel("users"));
        dto.add(linkTo(methodOn(UserController.class).update(dto.getId(), null)).withRel("update"));
        dto.add(linkTo(methodOn(UserController.class).delete(dto.getId())).withRel("delete"));
        return dto;
    }

    public Link createSelfLink(Long id) {
        return linkTo(methodOn(UserController.class).findById(id)).withSelfRel();
    }
}
