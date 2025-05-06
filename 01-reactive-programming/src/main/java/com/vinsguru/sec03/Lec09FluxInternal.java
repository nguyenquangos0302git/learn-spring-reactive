package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import com.vinsguru.sec03.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInternal {

    public static void main(String[] args) {

        Flux.interval(Duration.ofMillis(500))
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }

}
