package org.mixshr.orderscontrollers.utils;

import lombok.AllArgsConstructor;
import org.mixshr.orderscontrollers.dto.OrderDTO;
import org.mixshr.orderscontrollers.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderDTO toDTO(OrderEntity order) {
        return Objects.isNull(order) ? null : modelMapper.map(order, OrderDTO.class);
    }

    public List<OrderDTO> toDTO(List<OrderEntity> orders) {
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (OrderEntity order : orders) {
            orderDTOS.add(toDTO(order));
        }

        return orderDTOS;
    }

    public OrderEntity toEntity(OrderDTO orderDTO) {
        return Objects.isNull(orderDTO) ? null : modelMapper.map(orderDTO, OrderEntity.class);
    }

    public void toEntity(OrderDTO orderDTO, OrderEntity order) {
        if (Objects.isNull(orderDTO)) {
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }

        if (Objects.isNull(order)) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        modelMapper.map(orderDTO, order);
    }
}
