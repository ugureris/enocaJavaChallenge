package com.enoca.challenge.controller;

import com.enoca.challenge.entity.Product;
import com.enoca.challenge.service.CartService;
import com.enoca.challenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/{customerId}/products/{productId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long customerId, @PathVariable Long productId) {
        try {
            cartService.addProductToCart(customerId, productId);
            return ResponseEntity.ok("Product added to cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add product to cart: " + e.getMessage());
        }
    }

    @DeleteMapping("/{customerId}/products/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long customerId, @PathVariable Long productId) {
        try {
            cartService.removeProductFromCart(customerId, productId);
            return ResponseEntity.ok("Product removed from cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to remove product from cart: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
