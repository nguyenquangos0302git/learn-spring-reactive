package com.vinsguru.sec13;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;


public class Lec03ContextPropagation {

    private static final Logger logger = LoggerFactory.getLogger(Lec03ContextPropagation.class.getName());

    public static void main(String[] args) {
        getWelcomeMessage()
                .concatWith(Flux.merge(producer1().contextWrite(ctx -> Context.empty()), producer2()))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(2);
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            logger.info("{}", ctx);
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.<String>get("user")));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

    private static Mono<String> producer1() {
        return Mono.<String>deferContextual(ctx -> {
            logger.info("producer1: {}", ctx);
            return Mono.empty();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> producer2   () {
        return Mono.<String>deferContextual(ctx -> {
            logger.info("producer2: {}", ctx);
            return Mono.empty();
        }).subscribeOn(Schedulers.parallel());
    }

}
