 package com.vinsguru.sec08;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

 public class Lec05BackPressureStrategies {

     private static final Logger logger = LoggerFactory.getLogger(Lec05BackPressureStrategies.class);

     public static void main(String[] args) {

         System.setProperty("reactor.bufferSize.small", "16");

         var producer = Flux.create(sink -> {
                     for (int i = 1; i <= 100 && !sink.isCancelled(); i++) {
                         logger.info("generating: {}", i);
                         sink.next(i);
                         Util.sleep(Duration.ofMillis(50));
                     }
                     sink.complete();
                 })
                 .cast(Integer.class)
                 .subscribeOn(Schedulers.parallel());

         producer
//                 .onBackpressureBuffer()
//                 .onBackpressureError()
//                 .onBackpressureBuffer(10) //2 - 11
//                 .onBackpressureDrop()
                 .onBackpressureLatest()
                 .log()
                 .limitRate(1)
                 .publishOn(Schedulers.boundedElastic())
                 .map(Lec05BackPressureStrategies::timeConsumingTask)
                 .subscribe();

         Util.sleepSeconds(60);

     }

     private static int timeConsumingTask(int i) {
         logger.info("processing: {}", i);
         Util.sleepSeconds(1);
         return i;
     }

 }
