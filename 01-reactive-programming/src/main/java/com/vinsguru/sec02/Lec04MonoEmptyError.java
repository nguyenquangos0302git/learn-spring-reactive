package com.vinsguru.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {

    public static final Logger logger = LoggerFactory.getLogger(Lec04MonoEmptyError.class);

    public static void main(String[] args) {
        getUsername(3)
                .subscribe(
                        s -> System.out.println(s),
                        error -> System.out.println("error: " + error)
                );
    }

    private static Mono<String> getUsername(Integer userId) {
        return switch (userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }

}
