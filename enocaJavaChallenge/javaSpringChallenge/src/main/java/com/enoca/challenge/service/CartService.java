package com.enoca.challenge.service;

import com.enoca.challenge.entity.Cart;
import com.enoca.challenge.entity.Product;
import com.enoca.challenge.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public Cart getCart(Long customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    public Cart updateCart(Long cartId, Cart updatedCart) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart != null) {
            cart.setCartItems(updatedCart.getCartItems());
            cart.setTotalPrice(updatedCart.getTotalPrice());

            cartRepository.save(cart);
        }
        return cart;
    }

    public void emptyCart(Long customerId) {
        cartRepository.deleteByCustomerId(customerId);
    }

    public Cart getCartByCustomerId(Long customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    public void deleteCart(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        if (cart != null) {
            cartRepository.delete(cart);
        }
    }

    public void addProductToCart(Long customerId, Long productId) {
        Cart cart = cartRepository.findByCustomerId(customerId);

        if (cart == null) {
            cart = new Cart();
            cart = cartRepository.save(cart);
        }

        Product product = productService.getProduct(productId);

        if (product != null) {
            cart.addProduct(product);
        }
        updateCart(customerId, cart);
    }

    public void removeProductFromCart(Long customerId, Long productId) {
        Cart cart = cartRepository.findByCustomerId(customerId);

        if (cart != null) {
            Product productToRemove = productService.getProduct(productId);

            if (productToRemove != null) {
                cart.removeProduct(productToRemove);
            }
            updateCart(customerId, cart);
        }
    }


}
