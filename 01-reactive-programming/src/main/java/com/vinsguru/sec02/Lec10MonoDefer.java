package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Lec10MonoDefer {

    public static final Logger logger = LoggerFactory.getLogger(Lec10MonoDefer.class);

    public static void main(String[] args) {
        Mono.defer(() -> createPublisher());

        Util.sleepSeconds(1);
    }

    private static Mono<Integer> createPublisher() {
        logger.info("creating publisher");
        var list = List.of(1, 2, 3);
        Util.sleepSeconds(1);
        return Mono.fromSupplier(() -> sum(list));
    }

    // time-consuming business logic
    private static int sum(List<Integer> list) {
        logger.info("finding the sum of {}", list);
        Util.sleepSeconds(3);
        return list.stream().mapToInt(Integer::intValue).sum();
    }

}
