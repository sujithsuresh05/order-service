package com.food.ordering.system.payment.service.dataaccess.payment.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.payment.service.dataaccess.payment.entity.PaymentEntity;
import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.valuobject.PaymentId;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataAccessMapper {

    public PaymentEntity paymentToPaymentEntity(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getId().getValue())
                .customerId(payment.getCustomerId().getValue())
                .orderId(payment.getOrderId().getValue())
                .price(payment.getPrice().getAmount())
                .status(payment.getPaymentStatus())
                .createdAt(payment.getCreatedAt())
                .build();
    }

    public Payment paymentEntityToPayment(PaymentEntity paymentEntity) {
        return Payment.builder()
                .paymentId(new PaymentId(paymentEntity.getId()))
                .price(new Money(paymentEntity.getPrice()))
                .orderId(new OrderId(paymentEntity.getOrderId()))
                .customerId(new CustomerId(paymentEntity.getCustomerId()))
                .createdAt(paymentEntity.getCreatedAt())
                .paymentStatus(paymentEntity.getStatus())
                .build();
    }
}
