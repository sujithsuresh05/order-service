package com.food.ordering.system.order.service.domain.outbox.scheduler.approval;

import com.food.ordering.system.order.service.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval.RestaurantApprovalRequestMessagePublisher;
import com.food.ordering.system.outbox.OutBoxScheduler;
import com.food.ordering.system.outbox.OutBoxStatus;
import com.food.ordering.system.saga.SagaStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RestaurantApprovalOutBoxScheduler implements OutBoxScheduler {

    private final ApprovalOutboxHelper approvalOutboxHelper;
    private final RestaurantApprovalRequestMessagePublisher restaurantApprovalRequestMessagePublisher;

    public RestaurantApprovalOutBoxScheduler(ApprovalOutboxHelper approvalOutboxHelper,
                                             RestaurantApprovalRequestMessagePublisher restaurantApprovalRequestMessagePublisher) {
        this.approvalOutboxHelper = approvalOutboxHelper;
        this.restaurantApprovalRequestMessagePublisher = restaurantApprovalRequestMessagePublisher;
    }

    @Override
    @Transactional
    @Scheduled(fixedDelayString = "${order-service.outbox-scheduler-fixed-rate}",
            initialDelayString = "${order-service.outbox-scheduler-initial-delay}")
    public void processOutBoxMessage() {
        Optional<List<OrderApprovalOutboxMessage>> outboxApprovalMessageResponse = approvalOutboxHelper.getApprovalOutboxMessageByOutboxStatusAndSagaStatus(
                OutBoxStatus.STARTED,
                SagaStatus.PROCESSING);

        if (outboxApprovalMessageResponse.isPresent() && outboxApprovalMessageResponse.get().size() > 0) {
            List<OrderApprovalOutboxMessage> outBoxMessages = outboxApprovalMessageResponse.get();
            log.info("Received {} OrderApprovalOutboxMessage with ids: {}, sending to message bus!", outBoxMessages.size(),
                    outBoxMessages.stream().map(outBoxMessage ->
                            outBoxMessage.getId().toString()).collect(Collectors.toList()));
            outBoxMessages.forEach(outBoxMessage ->
                    restaurantApprovalRequestMessagePublisher.publish(outBoxMessage, this::updateOutBoxStatus));
            log.info("{} OrderApprovalOutboxMessage sent to message bus!", outBoxMessages.size());
        }
    }

    private void updateOutBoxStatus(OrderApprovalOutboxMessage orderApprovalOutboxMessage, OutBoxStatus outBoxStatus) {
        orderApprovalOutboxMessage.setOutBoxStatus(outBoxStatus);
        approvalOutboxHelper.save(orderApprovalOutboxMessage);
        log.info("OrderApprovalOutboxMessage is updated with outbox status: {}", outBoxStatus.name());
    }
}
