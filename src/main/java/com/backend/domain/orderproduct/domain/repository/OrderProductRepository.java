package com.backend.domain.orderproduct.domain.repository;

import com.backend.domain.orderproduct.domain.OrderProduct;
import com.backend.domain.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
    List<OrderProduct> findByOrder(Orders order);
}
