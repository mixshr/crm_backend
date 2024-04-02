package org.mixshr.orderscontrollers.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mixshr.orderscontrollers.dto.OrderDTO;
import org.mixshr.orderscontrollers.entity.UserEntity;
import org.mixshr.orderscontrollers.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public List<OrderDTO> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return orderService.findAllByUser((UserEntity) authentication.getPrincipal(), page, size);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(
            @PathVariable UUID id
    ) {
        return orderService.findById(id);
    }

    @PostMapping("/save")
    public OrderDTO saveOrder(
            @RequestBody OrderDTO order
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return orderService.save(order, (UserEntity) authentication.getPrincipal());
    }

    @PutMapping("/{id}")
    public OrderDTO updateOrder(
            @PathVariable UUID id,
            @RequestBody OrderDTO order
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return orderService.update(id, order, (UserEntity) authentication.getPrincipal());
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(
            @PathVariable UUID id
    ) {
        orderService.delete(id);
    }
}
