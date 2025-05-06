package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import com.vinsguru.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

public class Lec05TakeOperator {

    private static final Logger logger = LoggerFactory.getLogger(Lec05TakeOperator.class);

    public static void main(String[] args) {
        fluxTakeUntil();
    }

    private static void streamLimit() {
        IntStream.rangeClosed(1, 10).limit(3).forEach(System.out::println);
    }

    private static void fluxLimit() {
        Flux.range(1, 10)
                .take(3)
                .subscribe(Util.subscriber());
    }

    private static void fluxTakeWhile() {
        Flux.range(1, 10)
                .takeWhile(i -> i < 5)
                .subscribe(Util.subscriber());
    }

    private static void fluxTakeUntil() {
        Flux.range(1, 10)
                .takeUntil(i -> i > 5)
                .subscribe(Util.subscriber());
    }

}
