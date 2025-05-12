package com.vinsguru.sec07;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec08Parallel {

    private static final Logger logger = LoggerFactory.getLogger(Lec08Parallel.class);

    public static void main(String[] args) {

        logger.info("starting...");

        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.parallel())
                .map(Lec08Parallel::process)
//                .sequential()
                .map(i -> i + "a")
                .subscribe(Util.subscriber());

        Util.sleepSeconds(30);

    }

    private static int process(int i) {
        logger.info("time consuming task {}", i);
        Util.sleepSeconds(2);
        return i * 2;
    }

}
