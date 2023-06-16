package com.food.ordering.system.order.service.dataaccess.outbox.payment.mapper;

import com.food.ordering.system.order.service.dataaccess.outbox.payment.entity.PaymentOutBoxEntity;
import com.food.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class PaymentOutBoxDataaccessMapper {

    public PaymentOutBoxEntity orderPaymentOutboxMessageToOutboxEntity(OrderPaymentOutboxMessage orderPaymentOutboxMessage) {
        return PaymentOutBoxEntity.builder()
                .id(orderPaymentOutboxMessage.getId())
                .sagaId(orderPaymentOutboxMessage.getSagaId())
                .createdAt(orderPaymentOutboxMessage.getCreatedAt())
                .type(orderPaymentOutboxMessage.getType())
                .payload(orderPaymentOutboxMessage.getPayload())
                .orderStatus(orderPaymentOutboxMessage.getOrderStatus())
                .sagaStatus(orderPaymentOutboxMessage.getSagaStatus())
                .outboxStatus(orderPaymentOutboxMessage.getOutBoxStatus())
                .version(orderPaymentOutboxMessage.getVersion())
                .build();
    }

    public OrderPaymentOutboxMessage paymentOutboxEntityToOrderPaymentOutboxMessage(PaymentOutBoxEntity
                                                                                            paymentOutBoxEntity) {
        return OrderPaymentOutboxMessage.builder()
                .id(paymentOutBoxEntity.getId())
                .sagaId(paymentOutBoxEntity.getSagaId())
                .createdAt(paymentOutBoxEntity.getCreatedAt())
                .type(paymentOutBoxEntity.getType())
                .payload(paymentOutBoxEntity.getPayload())
                .orderStatus(paymentOutBoxEntity.getOrderStatus())
                .sagaStatus(paymentOutBoxEntity.getSagaStatus())
                .outBoxStatus(paymentOutBoxEntity.getOutboxStatus())
                .version(paymentOutBoxEntity.getVersion())
                .build();
    }
}
