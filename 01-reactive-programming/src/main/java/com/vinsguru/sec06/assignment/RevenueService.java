package com.vinsguru.sec06.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RevenueService implements OrderProcessor {

    private final Map<String, Integer> db = new HashMap<>();

    @Override
    public void consume(Order order) {
        var currentRevenue = db.getOrDefault(order.category(), 0);
        var updateRevenue = currentRevenue + order.price();
        db.put(order.category(), updateRevenue);
    }

    @Override
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> this.db.toString());
    }
}
