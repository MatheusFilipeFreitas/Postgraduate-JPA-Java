package com.mathffreitas.jpaunipds.model.dto.session;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Session", description = "Conference session")
public class SessionDto extends RepresentationModel<SessionDto> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Opening Keynote")
    private String title;

    @Schema(example = "2026-06-14")
    private LocalDate startDate;

    @Schema(example = "09:00:00")
    private LocalTime startTime;

    @Schema(example = "1")
    private Long conferenceId;
}
