package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCallable {

    public static final Logger logger = LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {
        var list = List.of(1, 2, 3);

        Mono.fromCallable(() -> sum(list))
                .subscribe(Util.subscriber());

        Mono.fromCallable(Lec06MonoFromCallable::getUsername)
                .subscribe(
                        System.out::println,
                        err -> System.out.println("error: " + err),
                        () -> System.out.println("completed")
                );
    }

    private static int sum(List<Integer> list) {
        logger.info("finding the sum of {}", list);
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    private static String getUsername() throws Exception {
        return "vins";
    }

}
