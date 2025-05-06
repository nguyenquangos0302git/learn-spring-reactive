package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04DelayElement {

    private static final Logger log = LoggerFactory.getLogger(Lec04DelayElement.class);

    public static void main(String[] args) {

       Flux.range(1, 10)
               .log()
               .delayElements(Duration.ofSeconds(1))
               .subscribe(Util.subscriber());

       Util.sleepSeconds(12);

    }

}
