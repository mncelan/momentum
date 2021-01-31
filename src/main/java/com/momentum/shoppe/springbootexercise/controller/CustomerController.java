package com.momentum.shoppe.springbootexercise.controller;

import com.momentum.shoppe.springbootexercise.exception.ResourceNotFoundException;
import com.momentum.shoppe.springbootexercise.model.Customer;
import com.momentum.shoppe.springbootexercise.model.Product;
import com.momentum.shoppe.springbootexercise.repository.CustomerRepository;
import com.momentum.shoppe.springbootexercise.repository.ProductRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    // Save Customer
    @PostMapping("/customer")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        Customer saveCustomer = customerRepository.save(customer);
        return  saveCustomer;
    }

    // Get Customers
    @GetMapping("/customer")
    public Page<Customer> listCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    // Get Customer by id
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
           Customer customer = customerRepository.findById(customerId)
                   .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: "+ customerId));
           return ResponseEntity.ok().body(customer);
        }

        // Get Customer Products
    @PutMapping("/customer/{id}/product")
    public ResponseEntity<Customer> getCustomerProduct(@PathVariable(value = "id") Long customerId,
                                       @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        var listOfProducts = new ArrayList<Long>();

        Customer savedCustomer = customerRepository.save(customerDetails);

        if (!savedCustomer.getProductList().isEmpty()) {
            savedCustomer.getProductList().forEach(p -> {

                listOfProducts.add(p.getPointsCost());
            });
        } else {
            throw new ResourceNotFoundException("No product(s) selected for customer ::" + savedCustomer.getId());
        }
        Long totalProducts = 0L;
        for (Long item : listOfProducts) {
            totalProducts = totalProducts + item;
        }
        if (totalProducts > savedCustomer.getPointsTotal()) {
                throw new ResourceNotFoundException("Customer does not have enough funds ::" + customer.getPointsTotal());
        } else {
            savedCustomer.setPointsTotal(savedCustomer.getPointsTotal() - totalProducts);
            customerRepository.save(customer);
        }

        return ResponseEntity.ok(customerRepository.save(savedCustomer));

    }

    // Update Customer by id
    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
                                                   @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: "+ customerId));

        customer.setFirstName(customerDetails.getFirstName());
        customer.setPointsTotal(customerDetails.getPointsTotal());

        return ResponseEntity.ok(customerRepository.save(customer));

    }


    // Delete Customer by id
    @DeleteMapping("/customer/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: "+ customerId));

        customerRepository.delete(customer);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Customer Deleted", Boolean.TRUE);

        return response;
    }
}
