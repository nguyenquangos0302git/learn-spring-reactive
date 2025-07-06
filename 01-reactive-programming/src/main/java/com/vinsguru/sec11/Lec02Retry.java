package com.vinsguru.sec11;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetrySpec;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec02Retry {

    private static final Logger logger = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {

        demo05();

        Util.sleepSeconds(60);
    }

    private static void demo01() {
        getCountryName()
                .retry(2)
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        getCountryName()
                .retry()
                .subscribe(Util.subscriber());
    }

    private static void demo03() {
        getCountryName()
                .retryWhen(Retry.max(2))
                .subscribe(Util.subscriber());
    }

    private static void demo04() {
        getCountryName()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(1)))
                .subscribe(Util.subscriber());
    }

    private static void demo05() {
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(1))
                                .filter(throwable -> RuntimeException.class.equals(throwable.getClass()))
                                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) -> retrySignal.failure()))

                )
                .subscribe(Util.subscriber());
    }


    private static Mono<String> getCountryName() {
        var atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
                    if (atomicInteger.incrementAndGet() < 3) {
                        throw new RuntimeException("Failed");
                    }
                    return Util.faker().country().name();
                })
                .doOnError(err -> logger.error("error: {}", err.getMessage()))
                .doOnSubscribe(s -> logger.info("subscribing"));
    }

}
