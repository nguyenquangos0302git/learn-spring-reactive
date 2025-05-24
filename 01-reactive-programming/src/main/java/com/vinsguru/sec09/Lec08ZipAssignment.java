package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.assignment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec08ZipAssignment {

    private static final Logger logger = LoggerFactory.getLogger(Lec08ZipAssignment.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        for (int i = 1; i < 10; i++) {
            client.getProduct(i).subscribe(Util.subscriber("product-" + i));
        }

        Util.sleepSeconds(60);

    }

}
