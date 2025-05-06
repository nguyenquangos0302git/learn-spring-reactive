package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import com.vinsguru.sec02.assignment.FileServiceImpl;
import com.vinsguru.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec12Assignment {

    public static final Logger logger = LoggerFactory.getLogger(Lec12Assignment.class);

    public static void main(String[] args) {

        var fileService = new FileServiceImpl();

        fileService.write("file.txt", "This is a test file").subscribe(Util.subscriber());

        fileService.read("file.txt").subscribe(Util.subscriber());

        fileService.delete("file.txt").subscribe(Util.subscriber());

    }

}
