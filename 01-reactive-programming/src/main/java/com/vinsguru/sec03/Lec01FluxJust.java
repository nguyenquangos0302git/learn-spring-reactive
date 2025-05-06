package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {

    public static void main(String[] args) {
        Flux.just(1, 2, 3, "a")
                .subscribe(Util.subscriber());
    }

}
