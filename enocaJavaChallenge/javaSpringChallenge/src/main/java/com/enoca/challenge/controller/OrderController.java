package com.enoca.challenge.controller;

import com.enoca.challenge.entity.Order;
import com.enoca.challenge.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/{customerId}")
    public void placeOrder(@PathVariable Long customerId, @RequestBody List<Long> productIds) {
        orderService.placeOrder(customerId, productIds);
    }

    @GetMapping("/{orderCode}")
    public Order getOrderForCode(@PathVariable String orderCode) {
        return orderService.getOrderForCode(orderCode);
    }

    @GetMapping("/customers/{customerId}")
    public List<Order> getAllOrdersForCustomer(@PathVariable Long customerId) {
        return orderService.getAllOrdersForCustomer(customerId);
    }

    @PutMapping("/{orderId}")
    public void updateOrder(@PathVariable Long orderId, @RequestBody Order order) {
        orderService.updateOrder(orderId, order);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

}
