package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.DomainConstants;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.enitiy.Order;
import com.food.ordering.system.order.service.domain.enitiy.OrderItem;
import com.food.ordering.system.order.service.domain.enitiy.Product;
import com.food.ordering.system.order.service.domain.enitiy.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order,
                                                      Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id {} paid", order.getId().getValue());
        return new OrderPaidEvent(order,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id {} approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order,
                                                  List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order id: {}", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id {} cancelled", order.getId().getValue());
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant with id " + restaurant.getId().getValue() + " is not currently not available");
        }
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        for (OrderItem item : order.getItems()) {
            restaurant.getProducts().forEach(restaurantProduct -> {
                Product currentProduct = item.getProduct();
                if (currentProduct.equals(restaurantProduct)) {
                    currentProduct.updateWithConfiguredNameAndPrice(restaurantProduct.getName(), restaurantProduct.getPrice());
                }
            });
        }
    }
}
