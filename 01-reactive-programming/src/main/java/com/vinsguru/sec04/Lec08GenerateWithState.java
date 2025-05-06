package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec08GenerateWithState {

    private static final Logger logger = LoggerFactory.getLogger(Lec08GenerateWithState.class);

    public static void main(String[] args) {
        demo01();
    }

    private static void demo01() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Flux.generate(synchronousSink -> {
            var country = Util.faker().country().name();
            synchronousSink.next(country);
            atomicInteger.incrementAndGet();
            if (atomicInteger.get() == 10 || country.equalsIgnoreCase("canada")) {
                synchronousSink.complete();
            }
        })
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        Flux.generate(
                () -> 0,
                (counter, sink) -> {
                    var country = Util.faker().country().name();
                    sink.next(country);
                    counter++;
                    if (counter == 10 || country.equalsIgnoreCase("canada")) {
                        sink.complete();
                    }
                    return counter;
                }
        ).subscribe(Util.subscriber());
    }

}
