package com.food.ordering.system.payment.service.ports.ouput.repository;

import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.dto.PaymentRequest;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    Payment savePayment(Payment payment);

    Optional<Payment> findByOrderId(UUID orderId);
}
