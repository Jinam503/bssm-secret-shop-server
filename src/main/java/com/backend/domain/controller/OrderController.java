package com.backend.domain.controller;

import com.backend.domain.dto.OrderInfoDto;
import com.backend.domain.model.Orders;
import com.backend.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderInfoDto>> getAllOrders() {
        List<OrderInfoDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/add_order")
    public ResponseEntity<Orders> addOrder(@RequestBody Orders order) {
        Orders newOrder = orderService.addOrder(order);
        return ResponseEntity.ok(newOrder);
    }

    @DeleteMapping("/delete_order/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return "Successfully deleted order";
    }

    @PutMapping("/update_accepted/{id}")
    public String updateAccepted(@PathVariable Long id) {
        orderService.updateAcceptedById(id);
        return "Successfully updated accepted order";
    }
}
