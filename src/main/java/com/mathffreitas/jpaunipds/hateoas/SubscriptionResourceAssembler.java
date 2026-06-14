package com.mathffreitas.jpaunipds.hateoas;

import com.mathffreitas.jpaunipds.controller.session.SessionController;
import com.mathffreitas.jpaunipds.controller.subscription.SubscriptionController;
import com.mathffreitas.jpaunipds.controller.user.UserController;
import com.mathffreitas.jpaunipds.model.dto.subscription.SubscriptionDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubscriptionResourceAssembler implements RepresentationModelAssembler<SubscriptionDto, SubscriptionDto> {

    @Override
    @NonNull
    public SubscriptionDto toModel(@NonNull SubscriptionDto dto) {
        dto.add(createSelfLink(dto.getUserId(), dto.getSessionId()));
        dto.add(linkTo(methodOn(SubscriptionController.class).findAll(null, null)).withRel("subscriptions"));
        dto.add(linkTo(methodOn(UserController.class).findById(dto.getUserId())).withRel("user"));
        dto.add(linkTo(methodOn(SessionController.class).findById(dto.getSessionId())).withRel("session"));
        dto.add(linkTo(methodOn(SubscriptionController.class).update(dto.getUserId(), dto.getSessionId(), null))
                .withRel("update"));
        dto.add(linkTo(methodOn(SubscriptionController.class).delete(dto.getUserId(), dto.getSessionId()))
                .withRel("delete"));
        return dto;
    }

    public Link createSelfLink(Long userId, Long sessionId) {
        return linkTo(methodOn(SubscriptionController.class).findById(userId, sessionId)).withSelfRel();
    }
}
