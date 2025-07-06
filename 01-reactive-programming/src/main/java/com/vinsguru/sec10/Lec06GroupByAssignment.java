package com.vinsguru.sec10;

import com.vinsguru.common.Util;
import com.vinsguru.sec10.assignment.groupby.OrderProcessingService;
import com.vinsguru.sec10.assignment.groupby.PurchaseOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec06GroupByAssignment {

    private static final Logger logger = LoggerFactory.getLogger(Lec06GroupByAssignment.class);

    public static void main(String[] args) {

        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf -> gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<PurchaseOrder> orderStream() {

        return Flux.interval(Duration.ofMillis(200))
                .map(i -> PurchaseOrder.creat());

    }
}
