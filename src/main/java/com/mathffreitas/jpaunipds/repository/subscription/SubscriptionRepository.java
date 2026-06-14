package com.mathffreitas.jpaunipds.repository.subscription;

import com.mathffreitas.jpaunipds.model.entity.subscription.Subscription;
import com.mathffreitas.jpaunipds.model.entity.subscription.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

    Optional<Subscription> findById_User_IdAndId_Session_Id(Long userId, Long sessionId);
}
