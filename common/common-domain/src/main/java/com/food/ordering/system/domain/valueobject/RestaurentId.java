package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

public class RestaurentId extends BaseId<UUID> {

    protected RestaurentId(UUID value) {
        super(value);
    }
}
