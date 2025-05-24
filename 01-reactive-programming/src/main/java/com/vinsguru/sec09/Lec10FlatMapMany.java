package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.applications.OrderService;
import com.vinsguru.sec09.applications.PaymentService;
import com.vinsguru.sec09.applications.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec10FlatMapMany {

    private static final Logger logger = LoggerFactory.getLogger(Lec10FlatMapMany.class);

    public static void main(String[] args) {

        UserService.getUserByName("mike")
                .flatMapMany(OrderService::getOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);

    }

}
