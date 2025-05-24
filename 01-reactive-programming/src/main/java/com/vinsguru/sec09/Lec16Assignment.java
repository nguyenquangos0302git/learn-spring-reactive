package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.applications.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Lec16Assignment {

    private static final Logger logger = LoggerFactory.getLogger(Lec16Assignment.class);

    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders) {
    }

    public static void main(String[] args) {
        UserService.getAllUsers()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(
                        PaymentService.getBalance(user.id()),
                        OrderService.getOrders(user.id()).collectList())
                .map(t -> new UserInformation(user.id(), user.username(), t.getT1(), t.getT2()));
    }

}
