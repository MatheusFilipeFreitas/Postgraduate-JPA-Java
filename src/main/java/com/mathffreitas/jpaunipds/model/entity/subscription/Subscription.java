package com.mathffreitas.jpaunipds.model.entity.subscription;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    @EmbeddedId
    private SubscriptionId id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "level")
    private Integer level;

    @Column(name = "unique_id", unique = true)
    private String uniqueId;
}
