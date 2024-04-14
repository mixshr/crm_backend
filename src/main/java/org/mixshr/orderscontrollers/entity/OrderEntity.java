package org.mixshr.orderscontrollers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.mixshr.orderscontrollers.enums.OrderStatus;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "orders")
@SQLRestriction("deleted_at is null")
@SQLDelete(sql = "update orders set deleted_at = now() where id =?")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEntity extends BaseEntity{

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "planned_at", nullable = false)
    private LocalDateTime plannedAt;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private UserEntity user;

    @Override
    public void checkFields() {
        if (getCreatedAt().isAfter(this.plannedAt)) {
            throw new RuntimeException("Дата планирования не должна быть меньше даты создания");
        }
    }
}