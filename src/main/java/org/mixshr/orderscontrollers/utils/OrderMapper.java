package org.mixshr.orderscontrollers.utils;

import org.mixshr.orderscontrollers.dto.OrderDTO;
import org.mixshr.orderscontrollers.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

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
}
