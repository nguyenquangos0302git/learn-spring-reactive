package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec11FluxDefer {

    private static final Logger logger = LoggerFactory.getLogger(Lec11FluxDefer.class);

    public static void main(String[] args) {

        Flux.defer(() -> Flux.fromIterable(createList()));

    }

    private static List<Integer> createList() {
        logger.info("creating list");
        return List.of(1);
    }

}
