package com.codecool.shop.service;

/**
 * Created by vbenedek on 2016.11.22..
 */
public class ProductService {
    private static ProductService ourInstance = new ProductService();

    public static ProductService getInstance() {
        return ourInstance;
    }

    private ProductService() {
    }
}
