package com.vinsguru.sec07;

import com.vinsguru.common.Util;
import com.vinsguru.sec07.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec07PublishOnSubscribeOn {

    private static final Logger logger = LoggerFactory.getLogger(Lec07PublishOnSubscribeOn.class);

    public static void main(String[] args) {

        logger.info("starting...");

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        logger.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .publishOn(Schedulers.newParallel("vins"))
                .doOnNext(v -> logger.info("value: {}", v))
                .doFirst(() -> logger.info("first1"))
                .publishOn(Schedulers.boundedElastic())
                .doFirst(() -> logger.info("first2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable);

        Util.sleepSeconds(2);

    }

}
