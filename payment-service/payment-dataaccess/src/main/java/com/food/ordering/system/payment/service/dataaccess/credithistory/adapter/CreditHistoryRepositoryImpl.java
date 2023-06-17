package com.food.ordering.system.payment.service.dataaccess.credithistory.adapter;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.payment.service.dataaccess.credithistory.mapper.CreditHistoryDataAccessMapper;
import com.food.ordering.system.payment.service.dataaccess.credithistory.repository.CreditHistoryJpaRepository;
import com.food.ordering.system.payment.service.dataaccess.credithistory.entity.CreditHistoryEntity;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.food.ordering.system.payment.service.domain.ports.output.repository.CreditHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreditHistoryRepositoryImpl implements CreditHistoryRepository {

    private final CreditHistoryDataAccessMapper creditHistoryDataAccessMapper;

    private final CreditHistoryJpaRepository creditHistoryJpaRepository;

    public CreditHistoryRepositoryImpl(CreditHistoryDataAccessMapper creditHistoryDataAccessMapper, CreditHistoryJpaRepository creditHistoryJpaRepository) {
        this.creditHistoryDataAccessMapper = creditHistoryDataAccessMapper;
        this.creditHistoryJpaRepository = creditHistoryJpaRepository;
    }

    @Override
    public CreditHistory save(CreditHistory creditHistory) {
        return creditHistoryDataAccessMapper.creditHistoryEntityToCreditHistory(creditHistoryJpaRepository
                .save(creditHistoryDataAccessMapper.creditHistoryToCreditHistoryEntity(creditHistory)));
    }

    @Override
    public Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId) {
        Optional<List<CreditHistoryEntity>> creditHistoryEntityList = creditHistoryJpaRepository.findByCustomerId(customerId.getValue());
        return creditHistoryEntityList.map(creditHistories ->
                creditHistories.stream()
                        .map(creditHistoryDataAccessMapper::creditHistoryEntityToCreditHistory)
                        .collect(Collectors.toList()));
    }
}
