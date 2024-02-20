package com.enoca.challenge.repository;

import com.enoca.challenge.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByCustomerId(Long customerId);

    void deleteByCustomerId(Long customerId);

}
