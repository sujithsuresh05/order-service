package com.food.ordering.system.payment.service.domain.event;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentCancelledEvent extends PaymentEvent{

    private final DomainEventPublisher<PaymentCancelledEvent> domainEventPublisher;

    public PaymentCancelledEvent(Payment payment, ZonedDateTime zonedDateTime, DomainEventPublisher<PaymentCancelledEvent> domainEventPublisher) {
        super(payment, zonedDateTime, Collections.EMPTY_LIST);
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void fire() {
        domainEventPublisher.publish(this);
    }
}
