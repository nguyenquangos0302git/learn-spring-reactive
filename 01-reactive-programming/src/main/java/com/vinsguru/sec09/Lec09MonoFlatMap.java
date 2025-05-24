package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.applications.PaymentService;
import com.vinsguru.sec09.applications.UserService;
import com.vinsguru.sec09.assignment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec09MonoFlatMap {

    private static final Logger logger = LoggerFactory.getLogger(Lec09MonoFlatMap.class);

    public static void main(String[] args) {

        UserService.getUserByName("sam")
                .flatMap(PaymentService::getBalance)
                .subscribe(Util.subscriber());
    }

}
