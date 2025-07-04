package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

public class Lec06Log {

    public static void main(String[] args) {

        Flux.range(1, 5)
                .log("rang-map")
                .map(i -> Util.faker().name().firstName())
                .log("map-subscribe")
                .subscribe(Util.subscriber());

    }

}
