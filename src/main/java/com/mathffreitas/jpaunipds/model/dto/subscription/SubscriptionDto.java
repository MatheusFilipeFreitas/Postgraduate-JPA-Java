package com.mathffreitas.jpaunipds.model.dto.subscription;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Subscription", description = "User subscription to a session")
public class SubscriptionDto extends RepresentationModel<SubscriptionDto> {

    @Schema(example = "1")
    private Long userId;

    @Schema(example = "1")
    private Long sessionId;

    @Schema(example = "2026-06-14T09:00:00")
    private LocalDateTime createdAt;

    @Schema(example = "1")
    private Integer level;

    @Schema(example = "f47ac10b-58cc-4372-a567-0e02b2c3d479")
    private String uniqueId;
}
