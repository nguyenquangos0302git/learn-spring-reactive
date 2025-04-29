package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec08MonoFromFuture {

    public static final Logger logger = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {
        Mono.fromFuture(getName())
                .subscribe(Util.subscriber());

        Mono.fromFuture(Lec08MonoFromFuture::getName);

        Util.sleepSeconds(1);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
           logger.info("generting name");
           return Util.faker().name().firstName();
        });
    }

}
