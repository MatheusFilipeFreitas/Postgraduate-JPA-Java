package com.mathffreitas.jpaunipds.model.dto.conference;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Conference", description = "Conference venue and metadata")
public class ConferenceDto extends RepresentationModel<ConferenceDto> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Spring Summit")
    private String name;

    @Schema(example = "123 Main Street")
    private String address;
}
