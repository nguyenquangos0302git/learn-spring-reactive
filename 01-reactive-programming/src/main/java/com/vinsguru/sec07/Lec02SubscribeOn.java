package com.vinsguru.sec07;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOn {

    private static final Logger logger = LoggerFactory.getLogger(Lec02SubscribeOn.class);

    public static void main(String[] args) {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        logger.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v -> logger.info("value: {}", v))
                .doFirst(() -> logger.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> logger.info("first2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));
        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub2"));

        Thread.ofPlatform().start(runnable);
        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(2);

    }

}
