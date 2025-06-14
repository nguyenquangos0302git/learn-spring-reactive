package com.vinsguru.sec07;

import com.vinsguru.common.Util;
import com.vinsguru.sec07.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06EventLoopIssueFix {

    private static final Logger logger = LoggerFactory.getLogger(Lec06EventLoopIssueFix.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        logger.info("starting...");

        for (int i = 1; i <= 5; i++) {
            client.getProductName(i)
                    .map(Lec06EventLoopIssueFix::process)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(20);

    }

    private static String process(String input) {
        Util.sleepSeconds(1);
        return input + "-processed";
    }

}
