package com.mathffreitas.jpaunipds.controller.conference;

import com.mathffreitas.jpaunipds.controller.common.BaseController;
import com.mathffreitas.jpaunipds.hateoas.ConferenceResourceAssembler;
import com.mathffreitas.jpaunipds.hateoas.SessionResourceAssembler;
import com.mathffreitas.jpaunipds.mapper.conference.ConferenceMapper;
import com.mathffreitas.jpaunipds.mapper.session.SessionMapper;
import com.mathffreitas.jpaunipds.model.dto.conference.ConferenceCreateDto;
import com.mathffreitas.jpaunipds.model.dto.conference.ConferenceDto;
import com.mathffreitas.jpaunipds.model.dto.conference.ConferenceUpdateDto;
import com.mathffreitas.jpaunipds.model.dto.session.SessionDto;
import com.mathffreitas.jpaunipds.model.entity.conference.Conference;
import com.mathffreitas.jpaunipds.service.conference.ConferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conferences")
@Tag(name = "Conferences", description = "Conference management endpoints")
public class ConferenceController extends BaseController<
        Conference,
        ConferenceDto,
        ConferenceCreateDto,
        ConferenceUpdateDto,
        ConferenceService,
        ConferenceMapper,
        ConferenceResourceAssembler> {

    private final SessionMapper sessionMapper;
    private final SessionResourceAssembler sessionAssembler;

    public ConferenceController(
            ConferenceService conferenceService,
            ConferenceMapper conferenceMapper,
            ConferenceResourceAssembler assembler,
            SessionMapper sessionMapper,
            SessionResourceAssembler sessionAssembler) {
        super(conferenceService, conferenceMapper, assembler);
        this.sessionMapper = sessionMapper;
        this.sessionAssembler = sessionAssembler;
    }

    @Operation(summary = "List sessions for a conference")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sessions retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Conference not found")
    })
    @GetMapping("/{id}/sessions")
    public ResponseEntity<PagedModel<SessionDto>> findSessions(
            @PathVariable Long id,
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable,
            @Parameter(hidden = true) PagedResourcesAssembler<SessionDto> pagedResourcesAssembler) {
        Page<SessionDto> sessions = service.findSessions(id, pageable)
                .map(sessionMapper::toDto);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(sessions, sessionAssembler));
    }
}
