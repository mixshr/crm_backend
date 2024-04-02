package org.mixshr.orderscontrollers.utils;

import org.mixshr.orderscontrollers.dto.OrderDTO;
import org.mixshr.orderscontrollers.entity.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderUtils {
    public static List<OrderDTO> toDTO(List<OrderEntity> orders) {
        List<OrderDTO> dto = new ArrayList<>();

        for (OrderEntity order : orders) {
            dto.add(toDTO(order));
        }

        return dto;
    }

    public static OrderDTO toDTO(OrderEntity order) {

        return new OrderDTO(
                order.getId(),
                order.getTitle(),
                order.getDescription(),
                order.getCreatedAt(),
                order.getPlannedAt(),
                order.getStatus()
        );
    }
}
