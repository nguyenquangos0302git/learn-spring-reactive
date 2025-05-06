package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import com.vinsguru.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class Lec03FluxSinkThreadSafety {

    private static final Logger logger = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);

    public static void main(String[] args) {
        demo02();
    }

    private static void demo01() {
        var list = new ArrayList<Integer>();

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }

        Util.sleepSeconds(3);

        logger.info("list size: {}", list.size());
    }

    private static void demo02() {
        var list = new ArrayList<String>();

        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }

        Util.sleepSeconds(3);

        logger.info("list size: {}", list.size());
    }

}
