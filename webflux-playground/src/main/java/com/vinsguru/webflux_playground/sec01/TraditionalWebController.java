package com.vinsguru.webflux_playground.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("traditional")
public class TraditionalWebController {

    private static final Logger logger = LoggerFactory.getLogger(TraditionalWebController.class);

    private final RestClient restClient = RestClient
            .builder()
            .baseUrl("http://localhost:7070")
            .build();

    @GetMapping("products")
    public List<Product> getProducts() {
        var list = this
                .restClient
                .get()
                .uri("/demo01/products")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>() {
                });
        logger.info("received response: {}", list);
        return list;
    }

    @GetMapping("products/notorious")
    public List<Product> getProductsDetail() {
        var list = this
                .restClient
                .get()
                .uri("/demo01/products/notorious")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>() {
                });
        logger.info("received response: {}", list);
        return list;
    }

    @GetMapping("products2")
    public Flux<Product> getProducts2() {
        var list = this
                .restClient
                .get()
                .uri("/demo01/products")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>() {
                });
        logger.info("received response: {}", list);
        return Flux.fromIterable(list);
    }


}
