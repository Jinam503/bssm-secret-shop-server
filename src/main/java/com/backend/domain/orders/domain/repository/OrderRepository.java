package com.backend.domain.orders.domain.repository;

import com.backend.domain.orders.domain.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByOrderByIdDesc(Pageable pageable);
}
