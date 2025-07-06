package com.vinsguru.sec12;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec06MulticastDirectAllOrNothing {

    private static final Logger logger = LoggerFactory.getLogger(Lec06MulticastDirectAllOrNothing.class);

    public static void main(String[] args) {

        demo01();

        Util.sleepSeconds(10);
    }

    private static void demo01() {

        System.setProperty("reactor.bufferSize.small", "16");

        var sink = Sinks.many().multicast().directAllOrNothing();
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 0; i < 100; i++) {
            var result = sink.tryEmitNext(i);
            logger.info("item: {}, result: {}", i, result);
        }

    }

}
