package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class OrderCreatedEventApplicationListener {

    private final OrderCreatedPaymentRequestPublisher orderCreatedPaymentRequestPublisher;

    public OrderCreatedEventApplicationListener(OrderCreatedPaymentRequestPublisher orderCreatedPaymentRequestPublisher) {
        this.orderCreatedPaymentRequestPublisher = orderCreatedPaymentRequestPublisher;
    }

    @TransactionalEventListener
    void process(OrderCreatedEvent orderCreatedEvent) {
        orderCreatedPaymentRequestPublisher.publish(orderCreatedEvent);
    }
}
