package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Subscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec05Subscribe.class);

    public static void main(String[] args) {

        demo03();

    }

    private static void demo01() {
        Flux.range(1, 10)
                .subscribe(
                        data -> log.info("received: {}", data),
                        error -> log.error("error: {}", error.getMessage()),
                        () -> log.info("completed"));
    }

    private static void demo02() {
        Flux.range(1, 10)
                .doOnNext(data -> log.info("received: {}", data))
                .doOnError(error -> log.error("error: {}", error.getMessage()))
                .doOnComplete(() -> log.info("completed"))
                .subscribe();
    }

    private static void demo03() {
        var flux = Flux.interval(Duration.ofMillis(500));
        var disposale = flux
                .doOnNext(data -> log.info("received: {}", data))
                .doOnError(error -> log.error("error: {}", error.getMessage()))
                .doOnComplete(() -> log.info("completed"))
                .subscribe();

        Util.sleepSeconds(1);
        disposale.dispose();
        log.info("disposed");
        Util.sleepSeconds(2);
    }

}
