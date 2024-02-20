package com.enoca.challenge.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Order extends BaseEntity{

    @ManyToOne
    private Customer customer;

    private String orderCode;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customerId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    private double totalPrice;

    public void addProduct(Product product) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItems.add(orderItem);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
