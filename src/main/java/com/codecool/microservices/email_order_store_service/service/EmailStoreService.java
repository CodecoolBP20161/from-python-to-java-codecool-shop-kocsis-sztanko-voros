package com.codecool.microservices.email_order_store_service.service;

public class EmailStoreService {
    private static EmailStoreService ourInstance = new EmailStoreService();

    public static EmailStoreService getInstance() {
        return ourInstance;
    }

    private EmailStoreService() {
    }
}
