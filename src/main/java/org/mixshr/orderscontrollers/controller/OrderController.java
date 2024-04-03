package org.mixshr.orderscontrollers.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mixshr.orderscontrollers.dto.OrderDTO;
import org.mixshr.orderscontrollers.entity.UserEntity;
import org.mixshr.orderscontrollers.service.OrderService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<OrderDTO>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(
                orderService.findAllByUser((UserEntity) authentication.getPrincipal(), page, size
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok().body(
                orderService.findById(id)
        );
    }

    @PostMapping("/save")
    public ResponseEntity<OrderDTO> saveOrder(
            @RequestBody OrderDTO order
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(
                orderService.save(order, (UserEntity) authentication.getPrincipal()
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(
            @PathVariable UUID id,
            @RequestBody OrderDTO order
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(
                orderService.update(id, order, (UserEntity) authentication.getPrincipal()
                )
        );
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(
            @PathVariable UUID id
    ) {
        orderService.delete(id);
    }
}