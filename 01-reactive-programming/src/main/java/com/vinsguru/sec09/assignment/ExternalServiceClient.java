package com.vinsguru.sec09.assignment;

import com.vinsguru.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<Product> getProduct(int productId) {
        return Mono
                .zip(getProductName(productId), getReview(productId), getPrice(productId))
                .map(tuple -> new Product(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }

    private Mono<String> getProductName(int productId) {
        return get("/demo01/product/" + productId);
    }

    private Mono<String> getReview(int productId) {
        return get("/demo01/review/" + productId);
    }

    private Mono<String> getPrice(int productId) {
        return get("/demo01/price/" + productId);
    }

    private Mono<String> get(String path) {
        return httpClient
                .get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }

}
