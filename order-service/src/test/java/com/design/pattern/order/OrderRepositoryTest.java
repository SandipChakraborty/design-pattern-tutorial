package com.design.pattern.order;

import com.design.pattern.order.model.Order;
import com.design.pattern.order.repo.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository repo;

    @Test
    void saveAndFindOrder() {
        Order o = new Order("First Order");
        repo.save(o);

        assertThat(repo.findAll())
            .hasSize(1)
            .first()
            .extracting(Order::getDescription)
            .isEqualTo("First Order");
    }
}
