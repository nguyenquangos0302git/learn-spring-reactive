package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import com.vinsguru.sec03.client.ExternalServiceClient;
import com.vinsguru.sec03.helper.NameGenerator;

public class Lec08NonBlockingStreamingMessage {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        client.getNames().subscribe(Util.subscriber("sub1"));
        client.getNames().subscribe(Util.subscriber("sub2"));
        Util.sleepSeconds(5);
    }

}
