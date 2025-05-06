package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import com.vinsguru.sec05.assignment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * We have 4 product ids - 1, 2, 3, 4
 * Get the product name using product-service
 * Timeout 2 seconds
    * call fallback for timeout service to get the product name
 * call fallback for empty service to get the product name in case of empty
 * Let the client class abstract timeout/empty handling!
    * client.getProductName(1)
 */

public class Lec11Assignment {

    private static final Logger log = LoggerFactory.getLogger(Lec11Assignment.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        for (int i = 1; i < 5; i++) {
            client.getProductName(i).subscribe(Util.subscriber());
        }

        Util.sleepSeconds(3);

    }


}
