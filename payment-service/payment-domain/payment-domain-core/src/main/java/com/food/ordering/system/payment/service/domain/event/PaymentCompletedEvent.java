package com.food.ordering.system.payment.service.domain.event;

import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class PaymentCompletedEvent extends PaymentEvent{


    public PaymentCompletedEvent(Payment payment,
                                 ZonedDateTime zonedDateTime) {
        super(payment, zonedDateTime, new ArrayList<>());
    }

}
