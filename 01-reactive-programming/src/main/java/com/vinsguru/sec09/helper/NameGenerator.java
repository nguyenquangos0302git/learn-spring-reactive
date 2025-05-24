package com.vinsguru.sec09.helper;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    private static final Logger logger = LoggerFactory.getLogger(NameGenerator.class);

    private final List<String> redis = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(synchronousSink -> {
                    logger.info("Generating name");
                    Util.sleepSeconds(1);
                    var name = Util.faker().name().firstName();
                    redis.add(name);
                    synchronousSink.next(name);
                })
                .startWith(redis)
                .cast(String.class);
    }

}
