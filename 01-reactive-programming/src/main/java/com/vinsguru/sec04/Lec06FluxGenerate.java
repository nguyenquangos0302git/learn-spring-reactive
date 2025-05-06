package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

public class Lec06FluxGenerate {

    private static final Logger logger = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            logger.info("invoked");
            synchronousSink.next(1);
        })
                .take(4)
                .subscribe(Util.subscriber());
    }


}
