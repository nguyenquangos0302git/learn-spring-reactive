package com.vinsguru.sec12;

import com.vinsguru.common.Util;
import reactor.core.publisher.Sinks;

public class Lec07Replay {

    public static void main(String[] args) {
        demo02();
    }

    private static void demo01() {

        var sink = Sinks.many().replay().limit(1);
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

        var sink = Sinks.many().replay().all();
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

}
