package com.vinsguru.sec11;

import com.vinsguru.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec01Repeat {

    public static void main(String[] args) {

        demo05();

        Util.sleepSeconds(60);

    }

    private static void demo01() {
        var mono = Mono.fromSupplier(() -> Util.faker().country().name());
        var subscriber = Util.subscriber();
        mono.repeat().subscribe(subscriber);
    }

    private static void demo02() {
        var mono = Mono.fromSupplier(() -> Util.faker().country().name());
        var subscriber = Util.subscriber();
        mono.repeat(3).subscribe(subscriber);
    }

    private static void demo03() {
        getCountryName()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void demo04() {
        var atomicInteger = new AtomicInteger(0);
        getCountryName()
                .repeat(() ->  atomicInteger.incrementAndGet() < 3)
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void demo05() {
        getCountryName()
                .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(2)).take(2))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        return Mono.fromSupplier(() -> Util.faker().country().name());
    }

}
