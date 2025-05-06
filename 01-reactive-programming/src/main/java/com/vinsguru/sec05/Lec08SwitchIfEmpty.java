package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec08SwitchIfEmpty {

    private static final Logger log = LoggerFactory.getLogger(Lec08SwitchIfEmpty.class);

    public static void main(String[] args) {

        Flux.empty()
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());

    }

    private static Flux<String> fallback() {
        return Flux.just("fallback");
    }

}
