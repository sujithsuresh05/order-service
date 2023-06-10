package com.food.ordering.system.payment.service.dataaccess.credithistory.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.payment.service.dataaccess.credithistory.entity.CreditHistoryEntity;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.food.ordering.system.payment.service.domain.valuobject.CreditHistoryId;
import org.springframework.stereotype.Component;

@Component
public class CreditHistoryDataAccessMapper {

    public CreditHistory creditHistoryEntityToCreditHistory(CreditHistoryEntity creditHistoryEntity) {
        return CreditHistory.builder()
                .customerId(new CustomerId(creditHistoryEntity.getCustomerId()))
                .transanctionType(creditHistoryEntity.getType())
                .creditHistoryId(new CreditHistoryId(creditHistoryEntity.getId()))
                .amount(new Money(creditHistoryEntity.getPrice()))
                .build();
    }

    public CreditHistoryEntity creditHistoryToCreditHistoryEntity(CreditHistory creditHistory) {
        return CreditHistoryEntity.builder()
                .id(creditHistory.getId().getValue())
                .price(creditHistory.getAmount().getAmount())
                .customerId(creditHistory.getCustomerId().getValue())
                .type(creditHistory.getTransanctionType())
                .build();
    }
}
