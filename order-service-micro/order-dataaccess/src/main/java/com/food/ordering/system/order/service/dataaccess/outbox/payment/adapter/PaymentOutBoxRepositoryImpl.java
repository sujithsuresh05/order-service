package com.food.ordering.system.order.service.dataaccess.outbox.payment.adapter;

import com.food.ordering.system.order.service.dataaccess.outbox.payment.exception.PaymentOutBoxNotFoundException;
import com.food.ordering.system.order.service.dataaccess.outbox.payment.mapper.PaymentOutBoxDataaccessMapper;
import com.food.ordering.system.order.service.dataaccess.outbox.payment.repository.PaymentOutBoxJpaRepository;
import com.food.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import com.food.ordering.system.order.service.domain.ports.output.repository.PaymentOutboxRepository;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PaymentOutBoxRepositoryImpl implements PaymentOutboxRepository {

    private final PaymentOutBoxJpaRepository paymentOutBoxJpaRepository;
    private final PaymentOutBoxDataaccessMapper paymentOutBoxDataaccessMapper;

    public PaymentOutBoxRepositoryImpl(PaymentOutBoxJpaRepository paymentOutBoxJpaRepository,
                                       PaymentOutBoxDataaccessMapper paymentOutBoxDataaccessMapper) {
        this.paymentOutBoxJpaRepository = paymentOutBoxJpaRepository;
        this.paymentOutBoxDataaccessMapper = paymentOutBoxDataaccessMapper;
    }

    @Override
    public OrderPaymentOutboxMessage save(OrderPaymentOutboxMessage orderPaymentOutboxMessage) {
        return paymentOutBoxDataaccessMapper
                .paymentOutboxEntityToOrderPaymentOutboxMessage(paymentOutBoxJpaRepository
                        .save(paymentOutBoxDataaccessMapper
                                .orderPaymentOutboxMessageToOutboxEntity(orderPaymentOutboxMessage)));
    }

    @Override
    public Optional<List<OrderPaymentOutboxMessage>> findByTypeAndOutboxStatusAndSagaStatus(String sagaType,
                                                                                            OutboxStatus outBoxStatus,
                                                                                            SagaStatus... sagaStatus) {
        return Optional.of(paymentOutBoxJpaRepository.findByTypeAndOutboxStatusAndSagaStatusIn(sagaType,
                        outBoxStatus,
                        Arrays.asList(sagaStatus))
                .orElseThrow(() -> new PaymentOutBoxNotFoundException("Payment outbox object " +
                        "could not be found for saga type " + sagaType))
                .stream()
                .map(paymentOutBoxDataaccessMapper::paymentOutboxEntityToOrderPaymentOutboxMessage)
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<OrderPaymentOutboxMessage> findByTypeAndSagaIdAndSagaStatus(String type,
                                                                                UUID sagaId,
                                                                                SagaStatus... sagaStatus) {
        return paymentOutBoxJpaRepository
                .findByTypeAndSagaIdAndSagaStatusIn(type, sagaId, Arrays.asList(sagaStatus))
                .map(paymentOutBoxDataaccessMapper::paymentOutboxEntityToOrderPaymentOutboxMessage);
    }

    @Override
    public void deleteByTypeAndOutboxStatusAndSagaStatus(String type,
                                                         OutboxStatus outBoxStatus,
                                                         SagaStatus... sagaStatus) {
        paymentOutBoxJpaRepository
                .deleteByTypeAndOutboxStatusAndSagaStatusIn(type, outBoxStatus, Arrays.asList(sagaStatus));
    }
}
