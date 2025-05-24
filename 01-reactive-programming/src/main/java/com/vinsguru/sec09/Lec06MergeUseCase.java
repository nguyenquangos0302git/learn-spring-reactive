package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.helper.Kayak;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec06MergeUseCase {

    private static final Logger logger = LoggerFactory.getLogger(Lec06MergeUseCase.class);

    public static void main(String[] args) {

        Kayak
                .getFlights()
                .subscribe(Util.subscriber("Kayak"));

        Util.sleepSeconds(60);

    }

}
