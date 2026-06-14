package com.mathffreitas.jpaunipds.service.subscription;

import com.mathffreitas.jpaunipds.exceptions.NotFoundException;
import com.mathffreitas.jpaunipds.model.entity.session.Session;
import com.mathffreitas.jpaunipds.model.entity.subscription.Subscription;
import com.mathffreitas.jpaunipds.model.entity.subscription.SubscriptionId;
import com.mathffreitas.jpaunipds.model.entity.user.User;
import com.mathffreitas.jpaunipds.repository.session.SessionRepository;
import com.mathffreitas.jpaunipds.repository.subscription.SubscriptionRepository;
import com.mathffreitas.jpaunipds.repository.user.UserRepository;
import com.mathffreitas.jpaunipds.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    @Override
    public Page<Subscription> findAll(Pageable pageable) {
        return subscriptionRepository.findAll(PageableUtils.sanitize(pageable, "createdAt"));
    }

    @Override
    public Subscription findById(Long userId, Long sessionId) {
        return subscriptionRepository.findById_User_IdAndId_Session_Id(userId, sessionId)
                .orElseThrow(() -> new NotFoundException("Subscription", userId + "/" + sessionId));
    }

    @Override
    @Transactional
    public Subscription create(Subscription subscription) {
        SubscriptionId id = buildSubscriptionId(subscription.getId());
        subscription.setId(id);
        subscription.setCreatedAt(LocalDateTime.now());
        subscription.setUniqueId(UUID.randomUUID().toString());
        return subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public Subscription update(Long userId, Long sessionId, Subscription subscription) {
        Subscription existing = findById(userId, sessionId);
        existing.setLevel(subscription.getLevel());
        return subscriptionRepository.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long userId, Long sessionId) {
        Subscription existing = findById(userId, sessionId);
        subscriptionRepository.delete(existing);
    }

    private SubscriptionId buildSubscriptionId(SubscriptionId id) {
        if (id == null || id.getUser() == null || id.getUser().getId() == null
                || id.getSession() == null || id.getSession().getId() == null) {
            throw new IllegalArgumentException("User id and session id are required");
        }

        Long userId = id.getUser().getId();
        Long sessionId = id.getSession().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User", userId));
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new NotFoundException("Session", sessionId));

        return new SubscriptionId(user, session);
    }
}
