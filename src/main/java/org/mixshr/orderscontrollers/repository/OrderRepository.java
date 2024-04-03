package org.mixshr.orderscontrollers.repository;

import org.mixshr.orderscontrollers.entity.OrderEntity;
import org.mixshr.orderscontrollers.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    Slice<OrderEntity> findAllByUser(UserEntity user, Pageable pageable);
}