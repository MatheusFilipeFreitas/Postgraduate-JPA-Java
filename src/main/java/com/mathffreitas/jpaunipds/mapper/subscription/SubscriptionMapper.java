package com.mathffreitas.jpaunipds.mapper.subscription;

import com.mathffreitas.jpaunipds.mapper.common.CreateDtoToEntityMapper;
import com.mathffreitas.jpaunipds.mapper.common.EntityToDtoMapper;
import com.mathffreitas.jpaunipds.mapper.common.UpdateDtoToEntityMapper;
import com.mathffreitas.jpaunipds.model.dto.subscription.SubscriptionCreateDto;
import com.mathffreitas.jpaunipds.model.dto.subscription.SubscriptionDto;
import com.mathffreitas.jpaunipds.model.dto.subscription.SubscriptionUpdateDto;
import com.mathffreitas.jpaunipds.model.entity.subscription.Subscription;

public interface SubscriptionMapper extends
        EntityToDtoMapper<Subscription, SubscriptionDto>,
        CreateDtoToEntityMapper<SubscriptionCreateDto, Subscription>,
        UpdateDtoToEntityMapper<SubscriptionUpdateDto, Subscription> {
}
