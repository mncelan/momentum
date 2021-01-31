package com.momentum.shoppe.springbootexercise.controller;


import com.momentum.shoppe.springbootexercise.exception.ResourceNotFoundException;
import com.momentum.shoppe.springbootexercise.model.Product;
import com.momentum.shoppe.springbootexercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Save a Product
    @PostMapping("/product")
    public Product createProduct(@Valid @RequestBody Product product) {
        Product saveProduct = productRepository.save(product);
        return  saveProduct;
    }

    // Get All Products
    @RequestMapping("/product")
    public Page<Product> listProduct(Pageable page) {
        return productRepository.findAll(page);
    }

    //Get Products by Id
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException{
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: "+ productId));
        return ResponseEntity.ok().body(product);
    }

    // Update Product by id
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
                                                   @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: "+ productId));

        product.setProductName(productDetails.getProductName());
        product.setProductCode(productDetails.getProductCode());
        product.setPointsCost(productDetails.getPointsCost());

        return ResponseEntity.ok(productRepository.save(product));

    }

    // Delete Product by id
    @DeleteMapping("/product/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: "+ productId));

        productRepository.delete(product);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Product Deleted", Boolean.TRUE);

        return response;
    }
}
