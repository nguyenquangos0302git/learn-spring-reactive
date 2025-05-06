package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import com.vinsguru.sec03.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec07FluxVsList {

    public static void main(String[] args) {

//       var list = NameGenerator.getNamesList(10);
//        System.out.println(list);
        NameGenerator.getNamesFlux(10).subscribe(Util.subscriber());

    }

}
