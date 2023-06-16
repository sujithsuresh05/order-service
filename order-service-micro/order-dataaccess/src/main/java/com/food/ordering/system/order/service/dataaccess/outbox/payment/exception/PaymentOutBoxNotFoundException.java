package com.food.ordering.system.order.service.dataaccess.outbox.payment.exception;

public class PaymentOutBoxNotFoundException extends RuntimeException{
    public PaymentOutBoxNotFoundException(String message) {
        super(message);
    }
}
