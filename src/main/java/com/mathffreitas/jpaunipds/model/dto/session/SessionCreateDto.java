package com.mathffreitas.jpaunipds.model.dto.session;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class SessionCreateDto {

    private String title;
    private LocalDate startDate;
    private LocalTime startTime;
    private Long conferenceId;
}
