package org.mixshr.orderscontrollers.service;

import lombok.AllArgsConstructor;
import org.mixshr.orderscontrollers.dto.OrderDTO;
import org.mixshr.orderscontrollers.entity.OrderEntity;
import org.mixshr.orderscontrollers.entity.UserEntity;
import org.mixshr.orderscontrollers.repository.OrderRepository;
import org.mixshr.orderscontrollers.utils.OrderMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDTO> findAllByUser(UserEntity user, int page, int size) {
       Pageable pageable = PageRequest.of(page, size, Sort.by("plannedAt"));
       return orderMapper.toDTO(orderRepository.findAllByUser(user, pageable).getContent());
    }

    public OrderDTO findById(UUID id) {
        OrderEntity order = orderRepository.findById(id).orElse(null);

        if (order == null) {
            return null;
        }

        return orderMapper.toDTO(order);
    }

    public OrderDTO save(OrderDTO order, UserEntity user) {
        OrderEntity orderEntity = new OrderEntity(
                order.getTitle(),
                order.getDescription(),
                order.getPlannedAt(),
                order.getStatus(),
                user
        );

        return orderMapper.toDTO(orderRepository.save(orderEntity));
    }

    public OrderDTO update(UUID id, OrderDTO order, UserEntity user) {
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);
        if (orderEntity == null) {
            return null;
        }

        orderEntity.setTitle(order.getTitle());
        orderEntity.setDescription(order.getDescription());
        orderEntity.setPlannedAt(order.getPlannedAt());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setUser(user);

        return orderMapper.toDTO(orderRepository.save(orderEntity));
    }

    public void delete(UUID id) {
        OrderEntity order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return;
        }

        orderRepository.delete(order);
    }
}