package com.vinsguru.sec02;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscriber {

    public static final Logger logger = LoggerFactory.getLogger(Lec03MonoSubscriber.class);

    public static void main(String[] args) {
        demo01();
        demo02();
        demo03();
        demo04();
    }

    public static void demo01() {
        var mono = Mono.just(1);
        mono.subscribe(i -> logger.info("received: {}", i));
    }

    public static void demo02() {
        var mono = Mono.just(1);
        mono.subscribe(
                i -> logger.info("received: {}", i),
                error -> logger.error("error: ", error),
                () -> logger.info("completed!"));
    }

    public static void demo03() {
        var mono = Mono.just(1);
        mono.subscribe(
                i -> logger.info("received: {}", i),
                error -> logger.error("error: ", error),
                () -> logger.info("completed!"),
                Subscription::cancel);
    }

    public static void demo04() {
        var mono = Mono.error(new RuntimeException("something went wrong"));
        mono.subscribe(
                i -> logger.info("received: {}", i),
                error -> logger.error("error: ", error),
                () -> logger.info("completed!"),
                Subscription::cancel);
    }

}
