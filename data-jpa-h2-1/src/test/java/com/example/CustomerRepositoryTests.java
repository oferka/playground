package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByLastName() {
        Customer customer = new Customer("first", "last");
        entityManager.persist(customer);
        List<Customer> findByLastName = customerRepository.findByLastName(customer.getLastName());
        assertThat(findByLastName).extracting(Customer::getLastName).containsOnly(customer.getLastName());
    }
}
