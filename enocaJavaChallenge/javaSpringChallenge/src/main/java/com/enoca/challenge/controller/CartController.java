package com.enoca.challenge.controller;

import com.enoca.challenge.entity.Cart;
import com.enoca.challenge.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{customerId}/products/{productId}")
    public void addProductToCart(@PathVariable Long customerId, @PathVariable Long productId) {
        cartService.addProductToCart(customerId, productId);
    }

    @DeleteMapping("/{customerId}/products/{productId}")
    public void removeProductFromCart(@PathVariable Long customerId, @PathVariable Long productId) {
        cartService.removeProductFromCart(customerId, productId);
    }

    @GetMapping("/{customerId}")
    public Cart getCartByCustomerId(@PathVariable Long customerId) {
        return cartService.getCartByCustomerId(customerId);
    }

    @PutMapping("/{customerId}")
    public Cart updateCart(@PathVariable Long customerId, @RequestBody Cart cart) {
        return cartService.updateCart(customerId, cart);
    }

    @DeleteMapping("/{customerId}")
    public void emptyCart(@PathVariable Long customerId) {
        cartService.emptyCart(customerId);
    }

}
