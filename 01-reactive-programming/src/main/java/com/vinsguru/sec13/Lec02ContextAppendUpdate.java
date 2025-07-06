package com.vinsguru.sec13;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec02ContextAppendUpdate {

    private static final Logger logger = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.delete("c"))
                .contextWrite(Context.of("c", "d").put("e", "f"))
                .contextWrite(Context.of("a", "b"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber("sub1"));
    }

    private static void replace() {
        getWelcomeMessage()
                .contextWrite(ctx -> Context.of("user", "mike"))
                .contextWrite(Context.of("c", "d").put("e", "f"))
                .contextWrite(Context.of("a", "b"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber("sub1"));
    }

    private static void append() {
        getWelcomeMessage()
                .contextWrite(Context.of("c", "d").put("e", "f"))
                .contextWrite(Context.of("a", "b"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber("sub1"));
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

}
