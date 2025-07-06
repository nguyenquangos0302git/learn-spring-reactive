package com.vinsguru.sec10;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec03Window {

    public static void main(String[] args) {

        demo02();

        Util.sleepSeconds(60);
    }

    private static void demo01() {
        eventStream2()
                .window(5)
                .flatMap(Lec03Window::processEvent)
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        eventStream2()
                .window(Duration.ofMillis(1800))
                .flatMap(Lec03Window::processEvent)
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream2() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event-" + i);
    }

    private static Mono<Void> processEvent(Flux<String> flux) {
        return flux.doOnNext(e -> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();
    }

}
