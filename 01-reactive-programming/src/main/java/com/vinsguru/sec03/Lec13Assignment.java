package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import com.vinsguru.sec03.assignment.StorePriceObserver;
import com.vinsguru.sec03.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Assignment:
 *  The stock service will emit price changes every 500ms for ~20 seconds.
 *  The price might change between 80-120.
 * Task:
 *  Create a subscriber with $1000 balance.
 *  Whenever the price drops below 90, you buy a stock. When the price goes above 110 you sell all the quantities and cancel the subscription.
 *  Print the profit you made!
 */

public class Lec13Assignment {

    private static final Logger logger = LoggerFactory.getLogger(Lec13Assignment.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        var subscriber = new StorePriceObserver();
        client.getPriceChanges().subscribe(subscriber);

        Util.sleepSeconds(20);
    }

}
