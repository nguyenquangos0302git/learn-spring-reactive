package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import com.vinsguru.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateDownstreamDemand {

    private static final Logger logger = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);

    public static void main(String[] args) {

        var subscriber = new SubscriberImpl();

        Flux.<String>create(fluxSink -> {

            for (int i = 0; i < 10; i++) {
                var name = Util.faker().name().firstName();
                logger.info("generated: {}", name);
                fluxSink.next(name);
            }

            fluxSink.complete();

        }).subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }

}
