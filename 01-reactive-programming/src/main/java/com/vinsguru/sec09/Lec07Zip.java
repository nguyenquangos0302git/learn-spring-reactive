package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.helper.Kayak;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07Zip {

    record Car(String body, String engine, String tires) {}

    private static final Logger logger = LoggerFactory.getLogger(Lec07Zip.class);

    public static void main(String[] args) {

        Flux.zip(getBody(), getEngine(), getTire())
                .map(t -> new Car(t.getT1(), t.getT2(), t.getT3()))
                .subscribe(Util.subscriber("zip"));

        Util.sleepSeconds(60);

    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body-" + i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 2)
                .map(i -> "engine-" + i)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTire() {
        return Flux.range(1, 10)
                .map(i -> "tire-" + i)
                .delayElements(Duration.ofMillis(75));
    }

}
