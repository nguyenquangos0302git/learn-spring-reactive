package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec06ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .map(data -> data == 5 || data == 7 ? data / 0 : data)
                .onErrorContinue((error, object) -> log.error("error: {}", error.getMessage()))
                .subscribe(Util.subscriber());

    }

    private static void onErrorReturn() {
        Flux.range(1, 10)
                .map(data -> data == 5 || data == 7 ? data / 0 : data)
                .onErrorReturn(ArithmeticException.class, -1)
                .onErrorReturn(IllegalArgumentException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    private static void onErrorResume() {
        Flux.range(1, 10)
                .map(data -> data == 5 || data == 7 ? data / 0 : data)
                .onErrorResume(IllegalArgumentException.class, error -> fallback())
                .onErrorResume(ArithmeticException.class, error -> Flux.just(-1))
                .onErrorResume(error -> fallback())
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(0, 100));
    }

    private static void onErrorComplete() {
        Flux.range(1, 10)
                .map(data -> data == 5 || data == 7 ? data / 0 : data)
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }

}
