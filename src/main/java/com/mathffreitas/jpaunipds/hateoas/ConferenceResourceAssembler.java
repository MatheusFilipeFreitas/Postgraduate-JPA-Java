package com.mathffreitas.jpaunipds.hateoas;

import com.mathffreitas.jpaunipds.controller.conference.ConferenceController;
import com.mathffreitas.jpaunipds.model.dto.conference.ConferenceDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ConferenceResourceAssembler implements RepresentationModelAssembler<ConferenceDto, ConferenceDto> {

    @Override
    @NonNull
    public ConferenceDto toModel(@NonNull ConferenceDto dto) {
        dto.add(createSelfLink(dto.getId()));
        dto.add(linkTo(methodOn(ConferenceController.class).findAll(null, null)).withRel("conferences"));
        dto.add(linkTo(methodOn(ConferenceController.class).findSessions(dto.getId(), null, null)).withRel("sessions"));
        dto.add(linkTo(methodOn(ConferenceController.class).update(dto.getId(), null)).withRel("update"));
        dto.add(linkTo(methodOn(ConferenceController.class).delete(dto.getId())).withRel("delete"));
        return dto;
    }

    public Link createSelfLink(Long id) {
        return linkTo(methodOn(ConferenceController.class).findById(id)).withSelfRel();
    }
}
