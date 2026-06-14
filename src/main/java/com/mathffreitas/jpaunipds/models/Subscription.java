package com.mathffreitas.jpaunipds.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_subscription")
@Getter
@Setter
@NoArgsConstructor
public class Subscription {

    @Id
    private SubscriptionId id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "level")
    private Integer level;

    @Column(name = "unique_id", unique = true)
    private String uniqueId;
}
