package com.vinsguru.sec12;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {

    private static final Logger log = LoggerFactory.getLogger(Lec01SinkOne.class);

    public static void main(String[] args) {
        demo03();
    }

    private static void demo01() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();

        mono.subscribe(Util.subscriber());

        sink.tryEmitValue("Hello");
//        sink.tryEmitEmpty();
//        sink.tryEmitError(new RuntimeException("error"));

    }

    private static void demo02() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();

        sink.tryEmitValue("Hello");

        mono.subscribe(Util.subscriber("sam"));
        mono.subscribe(Util.subscriber("mike"));
    }

    private static void demo03() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();

        sink.emitValue("Hi", (signalType, emitResult) -> {
            log.info("signalType: {}", signalType.name());
            log.info("emitResult: {}", emitResult.name());
            return false;
        });

        sink.emitValue("Hello", (signalType, emitResult) -> {
            log.info("hello");
            log.info("signalType: {}", signalType.name());
            log.info("emitResult: {}", emitResult.name());
            return false;
        });


        mono.subscribe(Util.subscriber("sam"));
    }

}
