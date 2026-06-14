package com.mathffreitas.jpaunipds.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tbl_session")
@Getter
@Setter
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;

//  Commented to create new props in the ref table:
//    @ManyToMany
//    @JoinTable(
//            name = "tbl_subscription",
//            joinColumns = @JoinColumn(name = "session_id"),
//            inverseForeignKey = @JoinColumn(name = "subscribed_user_id")
//    )
//    private List<User> users;
}
