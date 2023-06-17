package com.food.ordering.system.outbox;

public interface OutBoxScheduler {

    void processOutboxMessage();
}
