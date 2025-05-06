package com.vinsguru.sec06;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04HotPublisherCache {

    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {

        var stockStream = stockStream().replay(1).autoConnect(0);

        Util.sleepSeconds(4);
        log.info("sub1 joining");
        stockStream
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(4);
        log.info("sub2 joining");
        stockStream
                .subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(15);

    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10, 100))).take(10)
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("emitting: {}", price))
                .cast(Integer.class);
    }

}
