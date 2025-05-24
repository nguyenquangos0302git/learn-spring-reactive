package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.assignment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec14CollectList {

    private static final Logger logger = LoggerFactory.getLogger(Lec14CollectList.class);

    public static void main(String[] args) {
        Flux.range(1, 10)
                .collectList()
                .subscribe(Util.subscriber());
    }

}
