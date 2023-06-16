package com.food.ordering.system.order.service.dataaccess.outbox.restaurantapproval.adapter;

import com.food.ordering.system.order.service.dataaccess.outbox.restaurantapproval.exception.ApprovalOutboxNotFoundException;
import com.food.ordering.system.order.service.dataaccess.outbox.restaurantapproval.mapper.ApprovalOutboxDataaccessMapper;
import com.food.ordering.system.order.service.dataaccess.outbox.restaurantapproval.repository.ApprovalOutboxJpaRepository;
import com.food.ordering.system.order.service.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import com.food.ordering.system.order.service.domain.ports.output.repository.ApprovalOutboxRepository;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ApprovalOutboxRepositoryImpl implements ApprovalOutboxRepository {

    private final ApprovalOutboxJpaRepository approvalOutboxJpaRepository;
    private final ApprovalOutboxDataaccessMapper approvalOutboxDataaccessMapper;

    public ApprovalOutboxRepositoryImpl(ApprovalOutboxJpaRepository approvalOutboxJpaRepository,
                                        ApprovalOutboxDataaccessMapper approvalOutboxDataaccessMapper) {
        this.approvalOutboxJpaRepository = approvalOutboxJpaRepository;
        this.approvalOutboxDataaccessMapper = approvalOutboxDataaccessMapper;
    }

    @Override
    public OrderApprovalOutboxMessage save(OrderApprovalOutboxMessage orderApprovalOutboxMessage) {
        return approvalOutboxDataaccessMapper
                .approvalOutboxEntityToOrderApprovalOutboxMessage(approvalOutboxJpaRepository.save(
                        approvalOutboxDataaccessMapper.approvalOutboxMessageToOutboxEntity(orderApprovalOutboxMessage)));
    }

    @Override
    public Optional<List<OrderApprovalOutboxMessage>> findByTypeAndOutboxStatusAndSagaStatus(String sagaType, OutboxStatus outBoxStatus, SagaStatus... sagaStatus) {
        return Optional.of(
                approvalOutboxJpaRepository.findByTypeAndOutboxStatusAndSagaStatusIn(sagaType, outBoxStatus, Arrays.asList(sagaStatus))
                        .orElseThrow(() -> new ApprovalOutboxNotFoundException("Order Approval outbox object " +
                                "could not be found for saga type " + sagaType))
                        .stream()
                        .map(approvalOutboxDataaccessMapper::approvalOutboxEntityToOrderApprovalOutboxMessage)
                        .collect(Collectors.toList()));
    }

    @Override
    public Optional<OrderApprovalOutboxMessage> findByTypeAndSagaIdAndSagaStatus(String sagaType, UUID sagaId, SagaStatus... sagaStatus) {
        return approvalOutboxJpaRepository.findByTypeAndSagaIdAndSagaStatusIn(sagaType,
                        sagaId,
                        Arrays.asList(sagaStatus))
                .map(approvalOutboxDataaccessMapper::approvalOutboxEntityToOrderApprovalOutboxMessage);
    }

    @Override
    public void deleteByTypeAndOutboxStatusAndSagaStatus(String sagaType, OutboxStatus outboxStatus, SagaStatus... sagaStatus) {
        approvalOutboxJpaRepository.deleteByTypeAndOutboxStatusAndSagaStatusIn(sagaType, outboxStatus, Arrays.asList(sagaStatus));

    }
}
