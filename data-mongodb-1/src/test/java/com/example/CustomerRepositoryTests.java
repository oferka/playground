package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository repository;
    private Customer dave;
    private Customer oliver;
    private Customer carter;

    @Before
    public void setUp() {
        repository.deleteAll();
        dave = repository.save(new Customer("Dave", "Matthews"));
        oliver = repository.save(new Customer("Oliver August", "Matthews"));
        carter = repository.save(new Customer("Carter", "Beauford"));
    }

    @Test
    public void setsIdOnSave() {
        Customer dave = repository.save(new Customer("Dave", "Matthews"));
        assertThat(dave.id).isNotNull();
    }

    @Test
    public void findsByLastName() {
        List<Customer> result = repository.findByLastName("Beauford");
        assertThat(result).hasSize(1).extracting("firstName").contains("Carter");
    }

    @Test
    public void findsByExample() {
        Customer probe = new Customer(null, "Matthews");
        List<Customer> result = repository.findAll(Example.of(probe));
        assertThat(result).hasSize(2).extracting("firstName").contains("Dave", "Oliver August");
    }
}
