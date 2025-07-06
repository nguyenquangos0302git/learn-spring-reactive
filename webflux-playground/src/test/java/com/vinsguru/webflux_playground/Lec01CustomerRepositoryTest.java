package com.vinsguru.webflux_playground;

import com.vinsguru.webflux_playground.sec02.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

public class Lec01CustomerRepositoryTest extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(Lec01CustomerRepositoryTest.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findAll() {
        this.customerRepository.findAll()
                .doOnNext(c -> logger.info("{}", c))
                .as(StepVerifier::create)
                .expectNextCount(10)
                .expectComplete()
                .verify();
    }

    @Test
    public void findById() {
        this.customerRepository.findById(2)
                .doOnNext(c -> logger.info("{}", c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("mike", c.getName()))
                .expectComplete()
                .verify();
    }

    @Test
    public void findByName() {
        this.customerRepository.findByName("mike")
                .doOnNext(c -> logger.info("{}", c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("mike", c.getName()))
                .expectComplete()
                .verify();
    }

    @Test
    public void findByEmailEndingWith() {
        this.customerRepository.findByEmailEndingWith("ke@gmail.com")
                .doOnNext(c -> logger.info("{}", c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("mike", c.getName()))
                .assertNext(c -> Assertions.assertEquals("jake", c.getName()))
                .expectComplete()
                .verify();
    }

}
