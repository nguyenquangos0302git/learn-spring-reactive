package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import com.vinsguru.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11BlockingIO {

    public static final Logger logger = LoggerFactory.getLogger(Lec11BlockingIO.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        logger.info("starting...");

        for (int i = 1; i <= 5; i++) {
            var name = client.getProductName(i)
                    .block();
            logger.info(name);
        }

        Util.sleepSeconds(2);

    }

}
