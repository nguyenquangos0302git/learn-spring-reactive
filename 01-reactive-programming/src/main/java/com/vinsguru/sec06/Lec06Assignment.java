package com.vinsguru.sec06;

import com.vinsguru.common.Util;
import com.vinsguru.sec06.assignment.ExternalServiceClient;
import com.vinsguru.sec06.assignment.InventoryService;
import com.vinsguru.sec06.assignment.RevenueService;

import java.util.function.UnaryOperator;

public class Lec06Assignment {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderStream().subscribe(inventoryService::consume);
        client.orderStream().subscribe(revenueService::consume);

        inventoryService.stream().subscribe(Util.subscriber("inventory"));
        revenueService.stream().subscribe(Util.subscriber("revenue"));

        Util.sleepSeconds(30);
    }

}
