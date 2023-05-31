package com.food.ordering.system.order.service.domain.event;

import com.food.ordering.system.order.service.domain.enitiy.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {

    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }

}