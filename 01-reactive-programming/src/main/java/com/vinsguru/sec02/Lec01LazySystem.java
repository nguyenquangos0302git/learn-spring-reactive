package com.vinsguru.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Lec01LazySystem {

    public static final Logger logger = LoggerFactory.getLogger(Lec01LazySystem.class);

    public static void main(String[] args) {

        Stream.of(1)
                .peek(i -> logger.info("received: {}", i))
                .toList();

    }

}
