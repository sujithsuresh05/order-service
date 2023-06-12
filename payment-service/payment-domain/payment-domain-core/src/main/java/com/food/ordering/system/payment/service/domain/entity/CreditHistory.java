package com.food.ordering.system.payment.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.payment.service.domain.valuobject.CreditHistoryId;
import com.food.ordering.system.payment.service.domain.valuobject.TransanctionType;

public class CreditHistory extends BaseEntity<CreditHistoryId> {

    private final CustomerId customerId;
    private final Money amount;
    private final TransanctionType transanctionType;

    private CreditHistory(Builder builder) {
        setId(builder.creditHistoryId);
        customerId = builder.customerId;
        amount = builder.amount;
        transanctionType = builder.transanctionType;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public Money getAmount() {
        return amount;
    }

    public TransanctionType getTransanctionType() {
        return transanctionType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private CreditHistoryId creditHistoryId;
        private CustomerId customerId;
        private Money amount;
        private TransanctionType transanctionType;

        private Builder() {
        }



        public Builder creditHistoryId(CreditHistoryId creditHistoryId) {
            this.creditHistoryId = creditHistoryId;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder amount(Money val) {
            amount = val;
            return this;
        }

        public Builder transanctionType(TransanctionType val) {
            transanctionType = val;
            return this;
        }

        public CreditHistory build() {
            return new CreditHistory(this);
        }
    }
}
