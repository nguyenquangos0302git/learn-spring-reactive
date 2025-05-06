package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec09Timeout {

    private static final Logger log = LoggerFactory.getLogger(Lec09Timeout.class);

    public static void main(String[] args) {

        getProductName()
                .timeout(Duration.ofSeconds(2), fallback())
                .onErrorReturn("fallback")
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }

    private static Mono<String> getProductName() {
        return Mono
                .fromSupplier(() -> "service-" + Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(3));
    }

    private static Mono<String> fallback() {
        return Mono
                .fromSupplier(() -> "fallback-" + Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(1));
    }

}
