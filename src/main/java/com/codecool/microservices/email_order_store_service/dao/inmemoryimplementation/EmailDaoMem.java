package com.codecool.microservices.email_order_store_service.dao.inmemoryimplementation;

public class EmailDaoMem {
    private static EmailDaoMem ourInstance = new EmailDaoMem();

    public static EmailDaoMem getInstance() {
        return ourInstance;
    }

    private EmailDaoMem() {
    }
}
