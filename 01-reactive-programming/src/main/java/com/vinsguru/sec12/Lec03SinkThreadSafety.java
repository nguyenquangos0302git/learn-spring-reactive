package com.vinsguru.sec12;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Lec03SinkThreadSafety {

    private static final Logger logger = LoggerFactory.getLogger(Lec03SinkThreadSafety.class);

    public static void main(String[] args) {
        demo02();
    }

    private static void demo01() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        var list = new ArrayList<>();
        flux.subscribe(list::add);
        for (int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                sink.tryEmitNext(j);
            });
        }

        Util.sleepSeconds(2);
        logger.info("list size: {}", list.size());
    }

    private static void demo02() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();
        var flux = sink.asFlux();
        var list = new ArrayList<>();
        flux.subscribe(list::add);
        for (int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(j, (signalType, emitResult) -> {
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
                });
            });
        }

        Util.sleepSeconds(2);
        logger.info("list size: {}", list.size());
    }

}
