package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import com.vinsguru.sec04.assignment.FileReaderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * do the work only when it is subscribed
 * I do the work based on the demand
 * stop producing when subscriber cancels produce only the requested items
 * file should be closed once done
 */

public class Lec09Assignment {

    private static final Logger logger = LoggerFactory.getLogger(Lec09Assignment.class);

    public static void main(String[] args) {
        Path PATH = Path.of("src/main/resources/sec04/file.txt");
        var fileReaderService = new FileReaderServiceImpl();
        fileReaderService
                .read(PATH)
                .take(1)
                .subscribe(Util.subscriber());
    }


}
