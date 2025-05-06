package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01Handle {

    private static final Logger logger = LoggerFactory.getLogger(Lec01Handle.class);

    public static void main(String[] args) {
        demo02();
    }

    private static void demo01() {
        Flux<Integer> flux = Flux.range(1, 10);
        flux.handle((state, sink) -> {
                    switch (state) {
                        case 1 -> sink.next(-2);
                        case 4 -> {
                        }
                        case 7 -> sink.error(new RuntimeException("oops"));
                        default -> sink.next(state);
                    }
                })
                .cast(Integer.class)
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        Flux.generate(synchronousSink -> {
                    synchronousSink.next(Util.faker().country().name());
                })
                .cast(String.class)
                .handle((state, sink) -> {
                    sink.next(state);
                    if (state.equalsIgnoreCase("canada")) {
                        sink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }

}
