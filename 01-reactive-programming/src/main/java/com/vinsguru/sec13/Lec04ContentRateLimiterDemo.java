package com.vinsguru.sec13;

import com.vinsguru.common.Util;
import com.vinsguru.sec13.client.ExternalServiceClient;
import reactor.util.context.Context;

public class Lec04ContentRateLimiterDemo {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for (int i = 1; i <= 20; i++) {
            client.getBook()
                    .contextWrite(Context.of("user", "sam"))
                    .subscribe(Util.subscriber("sub1"));
            Util.sleepSeconds(1);
        }

        Util.sleepSeconds(5);

    }

}
