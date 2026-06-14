package com.mathffreitas.jpaunipds.mapper.subscription;

import com.mathffreitas.jpaunipds.model.dto.subscription.SubscriptionCreateDto;
import com.mathffreitas.jpaunipds.model.dto.subscription.SubscriptionDto;
import com.mathffreitas.jpaunipds.model.dto.subscription.SubscriptionUpdateDto;
import com.mathffreitas.jpaunipds.model.entity.session.Session;
import com.mathffreitas.jpaunipds.model.entity.subscription.Subscription;
import com.mathffreitas.jpaunipds.model.entity.subscription.SubscriptionId;
import com.mathffreitas.jpaunipds.model.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapperImpl implements SubscriptionMapper {

    @Override
    public SubscriptionDto toDto(Subscription entity) {
        if (entity == null) {
            return null;
        }
        SubscriptionDto dto = new SubscriptionDto();
        if (entity.getId() != null) {
            if (entity.getId().getUser() != null) {
                dto.setUserId(entity.getId().getUser().getId());
            }
            if (entity.getId().getSession() != null) {
                dto.setSessionId(entity.getId().getSession().getId());
            }
        }
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setLevel(entity.getLevel());
        dto.setUniqueId(entity.getUniqueId());
        return dto;
    }

    @Override
    public Subscription fromCreateDto(SubscriptionCreateDto dto) {
        if (dto == null) {
            return null;
        }
        Subscription entity = new Subscription();
        entity.setId(buildSubscriptionId(dto.getUserId(), dto.getSessionId()));
        entity.setLevel(dto.getLevel());
        return entity;
    }

    @Override
    public Subscription fromUpdateDto(SubscriptionUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        Subscription entity = new Subscription();
        entity.setLevel(dto.getLevel());
        return entity;
    }

    private SubscriptionId buildSubscriptionId(Long userId, Long sessionId) {
        SubscriptionId id = new SubscriptionId();
        if (userId != null) {
            User user = new User();
            user.setId(userId);
            id.setUser(user);
        }
        if (sessionId != null) {
            Session session = new Session();
            session.setId(sessionId);
            id.setSession(session);
        }
        return id;
    }
}
