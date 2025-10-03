package com.design.pattern.customer.controller;

import com.design.pattern.customer.model.Customer;
import com.design.pattern.customer.repo.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository repo;

    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Customer> all() {
        return repo.findAll();
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return repo.save(customer);
    }
}
