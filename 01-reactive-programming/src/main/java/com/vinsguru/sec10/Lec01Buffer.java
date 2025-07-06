package com.vinsguru.sec10;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {

    public static void main(String[] args) {

        demo05();

        Util.sleepSeconds(60);

    }

    private static void demo01() {
        eventStream()
                .buffer()
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        eventStream()
                .buffer(3)
                .subscribe(Util.subscriber());
    }

    private static void demo03() {
        eventStream1()
                .buffer(Duration.ofMillis(500))
                .subscribe(Util.subscriber());
    }

    private static void demo04() {
        eventStream2()
                .buffer(3)
                .subscribe(Util.subscriber());
    }

    private static void demo05() {
        eventStream2()
                .bufferTimeout(3, Duration.ofSeconds(1))
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .map(i -> "event-" + i);
    }

    private static Flux<String> eventStream1() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> "event-" + i);
    }

    private static Flux<String> eventStream2() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .concatWith(Flux.never())
                .map(i -> "event-" + i);
    }

}
