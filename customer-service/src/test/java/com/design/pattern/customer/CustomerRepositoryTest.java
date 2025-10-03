package com.design.pattern.customer;

import com.design.pattern.customer.model.Customer;
import com.design.pattern.customer.repo.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository repo;

    @Test
    void saveAndFindCustomer() {
        Customer c = new Customer("Alice");
        repo.save(c);

        assertThat(repo.findAll())
            .hasSize(1)
            .first()
            .extracting(Customer::getName)
            .isEqualTo("Alice");
    }
}
