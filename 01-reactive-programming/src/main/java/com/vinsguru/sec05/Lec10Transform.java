package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Lec10Transform {

    private static final Logger log = LoggerFactory.getLogger(Lec10Transform.class);

    record Customer(int id, String name) {}
    record PurchaseOrder(String productName, int price, int quantity) {}

    public static void main(String[] args) {

        var isDebugger = false;

        getCustomers()
                .transform(isDebugger ? addDebugger() : Function.identity())
                .subscribe();

        getPurchaseOrders()
                .transform(addDebugger())
                .subscribe();
    }

    private static Flux<Customer> getCustomers() {
        return Flux.range(1, 10)
                .map(i -> new Customer(i, Util.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchaseOrders() {
        return Flux.range(1, 10)
                .map(i -> new PurchaseOrder(Util.faker().commerce().productName(), i * 100, i));
    }

    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return flux -> flux.doOnNext(data -> log.info("received: {}", data))
                                    .doOnError(error -> log.error("error: {}", error.getMessage()))
                                    .doOnComplete(() -> log.info("completed"));
    }

}
