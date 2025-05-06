package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec12FluxMonoConversion {

    private static final Logger logger = LoggerFactory.getLogger(Lec12FluxMonoConversion.class);

    public static void main(String[] args) {
        var flux = Flux.range(1, 10);
        flux.next().subscribe(Util.subscriber());

    }

    private static void monoToFlux() {
        var mono = getUsername(1);
        save(Flux.from(mono));
    }

    private static Mono<String> getUsername(Integer userId) {
        return switch (userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }

    private static void save(Flux<String> flux) {
        flux.subscribe(Util.subscriber());
    }

}
