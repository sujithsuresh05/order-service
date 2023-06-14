package com.food.ordering.system.order.service.dataaccess.order.entity;

import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.outbox.OutBoxStatus;
import com.food.ordering.system.saga.SagaStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant_approval_outbox")
@Entity
public class ApprovalOutboxEntity {

    @Id
    private UUID id;
    private UUID sagaId;
    private ZonedDateTime createdAt;
    private ZonedDateTime precessedAt;
    private String type;
    private String payload;
    @Enumerated(EnumType.STRING)
    private SagaStatus sagaStatus;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private OutBoxStatus outBoxStatus;
    @Version
    private int version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprovalOutboxEntity that = (ApprovalOutboxEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
