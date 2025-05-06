package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05FluxFromRange {

    public static void main(String[] args) {

        Flux.range(1, 10).subscribe(Util.subscriber());

        Flux.range(1, 10)
                .map(data -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());

    }

}
