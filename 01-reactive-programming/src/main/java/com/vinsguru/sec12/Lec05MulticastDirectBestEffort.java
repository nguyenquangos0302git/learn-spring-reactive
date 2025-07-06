package com.vinsguru.sec12;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.time.Duration;
import java.util.Queue;

public class Lec05MulticastDirectBestEffort {

    private static final Logger logger = LoggerFactory.getLogger(Lec05MulticastDirectBestEffort.class);

    public static void main(String[] args) {

        demo02();

        Util.sleepSeconds(10);
    }

    private static void demo01() {

        System.setProperty("reactor.bufferSize.small", "16");

        var sink = Sinks.many().multicast().onBackpressureBuffer();
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 0; i < 100; i++) {
            var result = sink.tryEmitNext(i);
            logger.info("item: {}, result: {}", i, result);
        }

    }

    private static void demo02() {

        System.setProperty("reactor.bufferSize.small", "16");

        var sink = Sinks.many().multicast().directBestEffort();
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.onBackpressureBuffer().delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 0; i < 100; i++) {
            var result = sink.tryEmitNext(i);
            logger.info("item: {}, result: {}", i, result);
        }

    }

}
