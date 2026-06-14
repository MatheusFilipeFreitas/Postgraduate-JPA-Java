package com.mathffreitas.jpaunipds.model.dto.subscription;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionCreateDto {

    private Long userId;
    private Long sessionId;
    private Integer level;
}
