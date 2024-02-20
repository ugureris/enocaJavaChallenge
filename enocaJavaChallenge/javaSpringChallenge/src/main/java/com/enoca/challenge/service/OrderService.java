package com.enoca.challenge.service;

import com.enoca.challenge.entity.Order;
import com.enoca.challenge.entity.OrderItem;
import com.enoca.challenge.entity.Product;
import com.enoca.challenge.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public void placeOrder(Long customerId, List<Long> productIds) {
        Order order = new Order();
        order.setCustomerId(customerId);

        for (Long productId : productIds) {
            Product product = productService.getProduct(productId);
            if (product != null) {
                order.addProduct(product);
            }
        }
        orderRepository.save(order);
    }

    private double calculateOrderTotal(Order order) {
        double total = 0;
        for (OrderItem item : order.getOrderItems()) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void updateOrderTotal(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            double total = calculateOrderTotal(order);
            order.setTotalPrice(total);
            orderRepository.save(order);
        }
    }

    public void updateOrder(Long orderId, Order updatedOrder) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setOrderItems(updatedOrder.getOrderItems());
            orderRepository.save(order);
        }
    }

    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order getOrderForCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

}
