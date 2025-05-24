package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Lec15Then {

    private static final Logger logger = LoggerFactory.getLogger(Lec15Then.class);

    public static void main(String[] args) {

        var recrods = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

        saveRecords(recrods)
                .then(sendNotification(recrods))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }

    private static Flux<String> saveRecords(List<String> records) {
        return Flux.fromIterable(records)
                .map(r -> "saved: " + r)
                .delayElements(Duration.ofMillis(500));
    }

    private static Mono<Void> sendNotification(List<String> records) {
        return Mono.fromRunnable(() -> logger.info("all these {} records saved successfully", records));
    }

}
