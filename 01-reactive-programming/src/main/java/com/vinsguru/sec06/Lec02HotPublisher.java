package com.vinsguru.sec06;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec02HotPublisher {

    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {

//        var movieStream = movieStream().share();
//        var movieStream = movieStream().publish().refCount(1);
        var movieStream = movieStream().publish();
        movieStream.connect();

        Util.sleepSeconds(2);
        movieStream
                .take(6)
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
