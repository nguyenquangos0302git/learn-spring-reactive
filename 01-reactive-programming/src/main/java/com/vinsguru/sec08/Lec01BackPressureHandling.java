package com.vinsguru.sec08;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec01BackPressureHandling {

    private static final Logger logger = LoggerFactory.getLogger(Lec01BackPressureHandling.class);

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        var producer = Flux.generate(
                () -> 1,
                (state, sink) -> {
                    logger.info("generating: {}", state);
                    sink.next(state);
                    return ++state;
                }
        ).cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .publishOn(Schedulers.boundedElastic())
                .map(Lec01BackPressureHandling::timeConsumingTask)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

    private static int timeConsumingTask(int i) {
        Util.sleepSeconds(1);
        return i;
    }

}
