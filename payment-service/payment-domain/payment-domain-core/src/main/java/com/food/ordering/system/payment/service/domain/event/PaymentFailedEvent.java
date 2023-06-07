package com.food.ordering.system.payment.service.domain.event;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.List;

public class PaymentFailedEvent extends PaymentEvent{

    private final DomainEventPublisher<PaymentFailedEvent> domainEventPublisher;

    public PaymentFailedEvent(Payment payment, ZonedDateTime zonedDateTime,
                              List<String> failureMessages,
                              DomainEventPublisher<PaymentFailedEvent> domainEventPublisher) {
        super(payment, zonedDateTime, failureMessages);
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    public void fire() {
        domainEventPublisher.publish(this);
    }
}
