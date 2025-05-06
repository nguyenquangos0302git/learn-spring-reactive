package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

public class Lec02MultipleSubscriber {

    public static void main(String[] args) {

        var flux = Flux.just(1, 2, 3, 4, 5, 6);

        flux.subscribe(Util.subscriber("sub1"));
        flux.filter(i -> i % 2 == 0).subscribe(Util.subscriber("sub2"));
    }

}
