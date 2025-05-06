package com.vinsguru.sec06;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03HotPublisherAutoConnect {

    private static final Logger log = LoggerFactory.getLogger(Lec03HotPublisherAutoConnect.class);

    public static void main(String[] args) {

        var movieStream = movieStream().publish().autoConnect(0);

        Util.sleepSeconds(2);
        movieStream
                .take(2)
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(5);
        movieStream
                .take(3)
                .subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(15);

    }

    private static Flux<String> movieStream() {
        return Flux.generate(
                        () -> {
                            log.info("received the request");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "movie scene " + state;
                            log.info("playing {}", scene);
                            sink.next(scene);
                            return ++state;
                        }
                ).take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }

}
