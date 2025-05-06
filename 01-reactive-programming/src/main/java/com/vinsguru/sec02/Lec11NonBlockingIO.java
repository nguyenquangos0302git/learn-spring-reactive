package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import com.vinsguru.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec11NonBlockingIO {

    public static final Logger logger = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        logger.info("starting...");

        for (int i = 1; i <= 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(2);

    }

}
