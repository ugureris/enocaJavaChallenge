package com.enoca.challenge.repository;

import com.enoca.challenge.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    Order findByOrderCode(String orderCode);
}
