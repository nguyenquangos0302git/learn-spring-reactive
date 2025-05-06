package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec10FluxEmptyOrError {

    public static void main(String[] args) {

        Flux.empty()
                .subscribe(Util.subscriber());

        Flux.error(new RuntimeException("something went wrong"))
                .subscribe(Util.subscriber());

    }

}
