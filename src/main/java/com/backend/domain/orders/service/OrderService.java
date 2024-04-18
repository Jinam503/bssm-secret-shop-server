package com.backend.domain.orders.service;

import com.backend.domain.orderproduct.domain.OrderProduct;
import com.backend.domain.orderproduct.service.OrderProductService;
import com.backend.domain.orders.presentation.dto.OrderProductItemDto;
import com.backend.domain.orders.presentation.dto.OrderRequestDto;
import com.backend.domain.orders.presentation.dto.OrderResponseDto;
import com.backend.domain.orders.domain.Orders;
import com.backend.domain.product.service.ProductService;
import com.backend.domain.orders.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderProductService orderProductService;

    public List<OrderResponseDto> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> new OrderResponseDto(order, getOrderProductItemDtoList(order)))
                .toList();
    }

    private List<OrderProductItemDto> getOrderProductItemDtoList(Orders order) {
        return orderProductService.getOrderProductItemDtoList(order);
    }

    public void addOrder(OrderRequestDto orderRequestDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(orderRequestDto.orderDate(), formatter);

        Orders order= Orders.builder()
                .ordererName(orderRequestDto.ordererName())
                .needDelivery(orderRequestDto.needDelivery())
                .deliverPlace(orderRequestDto.deliverPlace())
                .totalPrice(orderRequestDto.totalPrice())
                .accepted(orderRequestDto.accepted())
                .orderDate(dateTime)
                .build();

        orderRepository.save(order);
        orderProductService.makeNewOrderProducts(order,orderRequestDto.productInfos());
    }

    public void deleteOrderById(Long id) {
        Orders order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
        orderProductService.deleteOrderProductById(order);

        orderRepository.deleteById(id);
    }

    public void updateAcceptedById(Long id) {
        Orders order = orderRepository.findById(id).orElseThrow(()->new RuntimeException(""));
        order.accept();
        orderRepository.save(order);
    }
}
