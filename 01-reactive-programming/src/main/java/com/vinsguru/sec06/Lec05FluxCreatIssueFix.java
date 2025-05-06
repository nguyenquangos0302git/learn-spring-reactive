package com.vinsguru.sec06;

import com.vinsguru.common.Util;
import com.vinsguru.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05FluxCreatIssueFix {

    private static final Logger log = LoggerFactory.getLogger(Lec05FluxCreatIssueFix.class);

    public static void main(String[] args) {

        var generator = new NameGenerator();
        var flux = Flux.create(generator).share();
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }

    }

}
