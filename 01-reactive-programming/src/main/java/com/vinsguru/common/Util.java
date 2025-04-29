package com.vinsguru.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Util {

    private static final Faker fake = new Faker();

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static Faker faker() {
        return fake;
    }

    public static void sleepSeconds(int second) {
        try {
            Thread.sleep(Duration.ofSeconds(second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
