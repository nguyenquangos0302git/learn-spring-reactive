package com.vinsguru.sec10;

import com.vinsguru.common.Util;
import com.vinsguru.sec10.assignment.window.FIleWrite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec05GroupedFlux {

    private static final Logger logger = LoggerFactory.getLogger(Lec05GroupedFlux.class);

    public static void main(String[] args) {

        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> i * 2)
                .startWith(1)
                .groupBy(i -> i % 2)
                .flatMap(Lec05GroupedFlux::processEvent)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Mono<Void> processEvent(GroupedFlux<Integer, Integer> groupedFlux) {
        logger.info("received flux for {}", groupedFlux.key());
        return groupedFlux
                .doOnNext(i -> logger.info("key: {}, item: {}", groupedFlux.key(), i))
                .doOnComplete(() -> logger.info("{} completed", groupedFlux.key()))
                .then();
    }

}
