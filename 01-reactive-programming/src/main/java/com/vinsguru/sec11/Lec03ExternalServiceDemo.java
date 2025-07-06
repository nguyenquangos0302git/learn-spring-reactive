package com.vinsguru.sec11;

import com.vinsguru.common.Util;
import com.vinsguru.sec11.client.ExternalServiceClient;
import com.vinsguru.sec11.client.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec03ExternalServiceDemo {

    private static final Logger logger = LoggerFactory.getLogger(Lec03ExternalServiceDemo.class);

    public static void main(String[] args) {
        retry();
        Util.sleepSeconds(60);
    }

    private static void repeat() {
        var client = new ExternalServiceClient();
        client.getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void retry() {
        var client = new ExternalServiceClient();
        client.getProductName(2)
                .retryWhen(retryWhenServerError())
                .subscribe(Util.subscriber());
    }

    private static Retry retryWhenServerError() {
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(throwable -> ServerError.class.equals(throwable.getClass()))
                .doBeforeRetry(rs -> logger.info("retrying... {}", rs.failure().getMessage()));

    }

}
