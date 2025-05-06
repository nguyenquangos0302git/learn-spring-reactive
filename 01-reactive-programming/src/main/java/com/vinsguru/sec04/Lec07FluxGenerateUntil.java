package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {

    private static final Logger logger = LoggerFactory.getLogger(Lec07FluxGenerateUntil.class);

    public static void main(String[] args) {
        demo03();
    }

    private static void demo01() {
        Flux.generate(synchronousSink -> {
            var country = Util.faker().country().name();
            synchronousSink.next(country);
            if (country.equalsIgnoreCase("canada")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }

    private static void demo02() {
        Flux.<String>generate(synchronousSink -> {
            var country = Util.faker().country().name();
            synchronousSink.next(country);
        })
                .takeUntil(country -> country.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void demo03() {
        Flux.<String>generate(synchronousSink -> {
                    var country = Util.faker().country().name();
                    synchronousSink.next(country);
                })
                .takeWhile(country -> !country.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

}
