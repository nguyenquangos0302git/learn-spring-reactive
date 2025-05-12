package com.vinsguru.sec07;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03MultipleSubscribeOn {

    private static final Logger logger = LoggerFactory.getLogger(Lec03MultipleSubscribeOn.class);

    public static void main(String[] args) {

        immediate();

    }

    private static void subscribeOn() {
        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        logger.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .subscribeOn(Schedulers.newParallel("vins"))
                .doOnNext(v -> logger.info("value: {}", v))
                .doFirst(() -> logger.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> logger.info("first2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable);

        Util.sleepSeconds(2);
    }

    private static void immediate() {
        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        logger.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v -> logger.info("value: {}", v))
                .doFirst(() -> logger.info("first1"))
                .subscribeOn(Schedulers.immediate())
                .doFirst(() -> logger.info("first2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable);

        Util.sleepSeconds(2);
    }

}
