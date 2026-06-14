package com.mathffreitas.jpaunipds.model.entity.session;

import com.mathffreitas.jpaunipds.model.entity.common.BaseEntity;
import com.mathffreitas.jpaunipds.model.entity.conference.Conference;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_session")
@AttributeOverride(name = "id", column = @Column(name = "session_id"))
@Getter
@Setter
@NoArgsConstructor
public class Session extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;
}
