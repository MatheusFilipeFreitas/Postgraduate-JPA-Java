package com.mathffreitas.jpaunipds.hateoas;

import com.mathffreitas.jpaunipds.controller.conference.ConferenceController;
import com.mathffreitas.jpaunipds.controller.session.SessionController;
import com.mathffreitas.jpaunipds.model.dto.session.SessionDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SessionResourceAssembler implements RepresentationModelAssembler<SessionDto, SessionDto> {

    @Override
    @NonNull
    public SessionDto toModel(@NonNull SessionDto dto) {
        dto.add(createSelfLink(dto.getId()));
        dto.add(linkTo(methodOn(SessionController.class).findAll(null, null)).withRel("sessions"));

        if (dto.getConferenceId() != null) {
            dto.add(linkTo(methodOn(ConferenceController.class).findById(dto.getConferenceId())).withRel("conference"));
        }

        dto.add(linkTo(methodOn(SessionController.class).update(dto.getId(), null)).withRel("update"));
        dto.add(linkTo(methodOn(SessionController.class).delete(dto.getId())).withRel("delete"));
        return dto;
    }

    public Link createSelfLink(Long id) {
        return linkTo(methodOn(SessionController.class).findById(id)).withSelfRel();
    }
}
