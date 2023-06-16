package com.food.ordering.system.order.service.domain.outbox.scheduler.payment;

import com.food.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.PaymentRequestMessagePublisher;
import com.food.ordering.system.outbox.OutBoxScheduler;
import com.food.ordering.system.outbox.OutboxStatus;
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
public class PaymentOutboxScheduler implements OutBoxScheduler {

    private final PaymentOutboxHelper paymentOutboxHelper;
    private final PaymentRequestMessagePublisher paymentRequestMessagePublisher;

    public PaymentOutboxScheduler(PaymentOutboxHelper paymentOutboxHelper,
                                  PaymentRequestMessagePublisher paymentRequestMessagePublisher) {
        this.paymentOutboxHelper = paymentOutboxHelper;
        this.paymentRequestMessagePublisher = paymentRequestMessagePublisher;
    }

    @Override
    @Transactional
    @Scheduled(fixedDelayString = "${order-service.outbox-scheduler-fixed-rate}",
            initialDelayString = "${order-service.outbox-scheduler-initial-delay}")
    public void processOutBoxMessage() {
        Optional<List<OrderPaymentOutboxMessage>> outboxMessageResponse = paymentOutboxHelper.getPaymentOutboxMessageByOutboxStatusAndSagaStatus(
                OutboxStatus.STARTED,
                SagaStatus.STARTED,
                SagaStatus.COMPENSATING);

        if (outboxMessageResponse.isPresent() && outboxMessageResponse.get().size() > 0) {
            List<OrderPaymentOutboxMessage> outBoxMessages = outboxMessageResponse.get();
            log.info("Received {} OrderPaymentOutboxMessage with ids: {}, sending to message bus!", outBoxMessages.size(),
                    outBoxMessages.stream().map(outBoxMessage ->
                            outBoxMessage.getId().toString()).collect(Collectors.toList()));
            outBoxMessages.forEach(outBoxMessage ->
                    paymentRequestMessagePublisher.publish(outBoxMessage, this::updateOutBoxStatus));
            log.info("{} OrderPaymentOutboxMessage sent to message bus!", outBoxMessages.size());
        }
    }

    private void updateOutBoxStatus(OrderPaymentOutboxMessage orderPaymentOutboxMessage, OutboxStatus outBoxStatus) {
        orderPaymentOutboxMessage.setOutBoxStatus(outBoxStatus);
        paymentOutboxHelper.save(orderPaymentOutboxMessage);
        log.info("OrderPaymentOutboxMessage is updated with outbox status: {}", outBoxStatus.name());
    }
}
