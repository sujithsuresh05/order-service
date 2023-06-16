package com.food.ordering.system.order.service.dataaccess.outbox.payment.repository;

import com.food.ordering.system.order.service.dataaccess.outbox.payment.entity.PaymentOutBoxEntity;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentOutBoxJpaRepository extends JpaRepository<PaymentOutBoxEntity, UUID> {

    Optional<List<PaymentOutBoxEntity>> findByTypeAndOutboxStatusAndSagaStatusIn(String type,
                                                                                 OutboxStatus outBoxStatus,
                                                                                 List<SagaStatus> sagaStatuses);

    Optional<PaymentOutBoxEntity> findByTypeAndSagaIdAndSagaStatusIn(String type,
                                                                     UUID sagaId,
                                                                     List<SagaStatus> sagaStatus);

    void deleteByTypeAndOutboxStatusAndSagaStatusIn(String type,
                                                    OutboxStatus outBoxStatus,
                                                    List<SagaStatus> sagaStatuses);
}
