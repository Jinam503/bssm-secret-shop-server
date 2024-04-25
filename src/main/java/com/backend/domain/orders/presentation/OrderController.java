package com.backend.domain.orders.presentation;

import com.backend.domain.orders.domain.Orders;
import com.backend.domain.orders.presentation.dto.OrderRequestDto;
import com.backend.domain.orders.presentation.dto.OrderResponseDto;
import com.backend.domain.orders.service.OrderService;
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
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/10_orders/{page_index}")
    public ResponseEntity<List<OrderResponseDto>> get10Orders(@PathVariable int page_index){
        List<OrderResponseDto> orders = orderService.get10OrdersFromIndex(page_index);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/add_order")
    public void addOrder(@RequestBody OrderRequestDto order) {
        System.out.println(order);
        orderService.addOrder(order);
    }

    @DeleteMapping("/delete_order/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }

    @PutMapping("/update_accepted/{id}")
    public void updateAccepted(@PathVariable Long id) {
        orderService.updateAcceptedById(id);
    }
}
