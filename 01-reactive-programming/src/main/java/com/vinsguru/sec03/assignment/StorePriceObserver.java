package com.vinsguru.sec03.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorePriceObserver implements Subscriber<Integer> {

    private static final Logger logger = LoggerFactory.getLogger(StorePriceObserver.class);
    private int quatity = 0;
    private int balance = 1000;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer price) {
        if (price < 90 && balance >= price) {
            quatity++;
            balance -= price;
            logger.info("Bought a stock at {}. total quantity: {}. remaining balance: {}", price, quatity, balance);
        } else if (price > 110 && quatity > 0) {
            logger.info("selling {} quantities at {}", quatity, price);
            balance+= price * quatity;
            quatity = 0;
            subscription.cancel();
            logger.info("profit: {}", balance - 1000);
        } else {
            logger.info("can not buy at: {}", price);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("error: ", throwable);
    }

    @Override
    public void onComplete() {
        logger.info("completed!");
    }
}
