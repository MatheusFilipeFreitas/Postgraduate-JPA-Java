package com.mathffreitas.jpaunipds.service.subscription;

import com.mathffreitas.jpaunipds.model.entity.subscription.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscriptionService {

    Page<Subscription> findAll(Pageable pageable);

    Subscription findById(Long userId, Long sessionId);

    Subscription create(Subscription subscription);

    Subscription update(Long userId, Long sessionId, Subscription subscription);

    void delete(Long userId, Long sessionId);
}
