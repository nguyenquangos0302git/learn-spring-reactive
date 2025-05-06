package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {

    public static void main(String[] args) {

        var list = List.of(1, 2, 3, 4, 5, 6);
        var stream = list.stream();

//        stream.forEach(System.out::println);
//        stream.forEach(System.out::println);

        var flux = Flux.fromStream(stream);
        flux.subscribe(Util.subscriber("sub1"));

        var flux1 = Flux.fromStream(list::stream);
        flux1.subscribe(Util.subscriber("sub2"));
        flux1.subscribe(Util.subscriber("sub3"));

    }

}
