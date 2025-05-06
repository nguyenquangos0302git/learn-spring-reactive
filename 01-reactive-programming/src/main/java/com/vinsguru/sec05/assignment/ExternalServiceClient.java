package com.vinsguru.sec05.assignment;

import com.vinsguru.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductName(int productId) {
        var defaultPath = "/demo03/product/" + productId;
        var timeoutPath = "/demo03/timeout-fallback/product/" + productId;
        var errorPath = "/demo03/error-fallback/product/" + productId;

        return getProductName(defaultPath)
                .timeout(Duration.ofSeconds(2), getProductName(timeoutPath))
                .switchIfEmpty(getProductName(errorPath));
    }

    private Mono<String> getProductName(String path) {
        return httpClient
                .get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }

}
