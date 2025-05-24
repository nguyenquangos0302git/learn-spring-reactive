package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.applications.OrderService;
import com.vinsguru.sec09.applications.User;
import com.vinsguru.sec09.applications.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11FluxFlatMap {

    private static final Logger logger = LoggerFactory.getLogger(Lec11FluxFlatMap.class);

    public static void main(String[] args) {
        demo02();
        Util.sleepSeconds(3);
    }

    private static void demo01() {
        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getOrders)
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getOrders, 1)
                .subscribe(Util.subscriber());
    }

}
