package com.vinsguru.sec10;

import com.vinsguru.common.Util;
import com.vinsguru.sec10.assignment.window.FIleWrite;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04WindowAssignment {

    public static void main(String[] args) {

        var counter = new AtomicInteger(0);
        var fileNameFormat = "src/main/resources/sec10/file%d.txt";

        eventStream2()
                .window(Duration.ofMillis(1800))
                .flatMap(flux -> FIleWrite.create(flux, Path.of(fileNameFormat.formatted(counter.getAndIncrement()))))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }

    private static Flux<String> eventStream2() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> "event-" + i);
    }

}
