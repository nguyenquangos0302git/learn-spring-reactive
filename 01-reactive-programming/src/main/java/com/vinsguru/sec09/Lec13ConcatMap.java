package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.assignment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec13ConcatMap {

    private static final Logger logger = LoggerFactory.getLogger(Lec13ConcatMap.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        Flux.range(1, 10)
                .concatMap(client::getProduct)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

}
