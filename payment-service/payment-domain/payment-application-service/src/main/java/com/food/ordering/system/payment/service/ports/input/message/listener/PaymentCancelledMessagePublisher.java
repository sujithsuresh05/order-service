package com.food.ordering.system.payment.service.ports.input.message.listener;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;

public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}
