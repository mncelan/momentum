package com.momentum.shoppe.springbootexercise.service;

import com.momentum.shoppe.springbootexercise.model.Customer;
import com.momentum.shoppe.springbootexercise.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer addCustomer(Customer user) {
        return repository.save(user);
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = repository.findAll();
        System.out.println("Getting data from DB : " + customers);
        return customers;
    }

    public void deleteUser(Customer customer) {
        repository.delete(customer);
    }
}
