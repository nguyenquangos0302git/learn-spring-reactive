package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.applications.OrderService;
import com.vinsguru.sec09.applications.User;
import com.vinsguru.sec09.applications.UserService;
import com.vinsguru.sec09.assignment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec12FluxFlatMapAssignment {

    private static final Logger logger = LoggerFactory.getLogger(Lec12FluxFlatMapAssignment.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        Flux.range(1, 10)
                .flatMap(client::getProduct, 3)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

}
