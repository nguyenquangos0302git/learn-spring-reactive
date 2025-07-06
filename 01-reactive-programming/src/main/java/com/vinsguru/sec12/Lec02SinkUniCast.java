package com.vinsguru.sec12;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

public class Lec02SinkUniCast {

    private static final Logger logger = LoggerFactory.getLogger(Lec02SinkUniCast.class);

    public static void main(String[] args) {

        demo02();

    }

    private static void demo01() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber());

    }

    private static void demo02() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

    }

}
