package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03ConcatWith {

    private static final Logger logger = LoggerFactory.getLogger(Lec03ConcatWith.class);

    public static void main(String[] args) {
        demo03();

        Util.sleepSeconds(60);

    }

    private static void demo01() {
        producer1()
                .concatWithValues(-1, 0)
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        producer1()
                .concatWith(producer2())
                .subscribe(Util.subscriber());
    }

    private static void demo03() {
        Flux.concat(producer1(), producer2())
                        .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(s -> logger.info("subscribing to producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .doOnSubscribe(s -> logger.info("subscribing to producer2"))
                .delayElements(Duration.ofMillis(10));
    }

}
