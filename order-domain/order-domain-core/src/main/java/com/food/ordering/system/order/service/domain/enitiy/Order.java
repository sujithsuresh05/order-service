package com.food.ordering.system.order.service.domain.enitiy;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.RestaurentId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;

import java.util.List;

public class Order extends AggregateRoot<OrderId> {

    private final CustomerId customerId;

    private final RestaurentId restaurentId;

    private final StreetAddress deliveryAddress;

    private final Money money;

    private final List<OrderItem> items;


}
