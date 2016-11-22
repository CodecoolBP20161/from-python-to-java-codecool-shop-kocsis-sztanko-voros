package com.codecool.shop.service;

/**
 * Created by vbenedek on 2016.11.22..
 */
public class SupplierService {
    private static SupplierService ourInstance = new SupplierService();

    public static SupplierService getInstance() {
        return ourInstance;
    }

    private SupplierService() {
    }
}
