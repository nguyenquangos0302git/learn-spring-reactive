package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec07MonoFromRunnable {

    public static final Logger logger = LoggerFactory.getLogger(Lec07MonoFromRunnable.class);

    public static void main(String[] args) {
        getProductName(2).subscribe(Util.subscriber());
    }

    private static Mono<String> getProductName(int productId) {
        if (1 == productId) {
            return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        }
        return Mono.fromRunnable(() -> notify(productId));
    }

    private static void notify(int productId) {
        logger.info("notifying unavailable product {}", productId);
    }

}
