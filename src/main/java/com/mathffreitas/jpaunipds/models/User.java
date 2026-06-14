package com.mathffreitas.jpaunipds.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @OneToOne(mappedBy = "user")
    private Subscription subscription;

//  Commented to create new props in the ref table:
//    @ManyToMany(mappedBy = "users")
//    private List<Session> sessions;
}
