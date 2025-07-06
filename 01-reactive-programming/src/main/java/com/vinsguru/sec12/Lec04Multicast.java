package com.vinsguru.sec12;

import com.vinsguru.common.Util;
import reactor.core.publisher.Sinks;

public class Lec04Multicast {

    public static void main(String[] args) {
        demo02();
    }

    private static void demo01() {

        var sink = Sinks.many().multicast().onBackpressureBuffer();
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("james"));
        sink.tryEmitNext("new message");
    }

    private static void demo02() {

        var sink = Sinks.many().multicast().onBackpressureBuffer();
        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("james"));
        sink.tryEmitNext("new message");
    }

}
