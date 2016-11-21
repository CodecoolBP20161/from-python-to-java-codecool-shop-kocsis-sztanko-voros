package com.codecool.shop.model;


import java.util.List;

public interface ShoppingCartInterface {

    void add(Product product);
    LineItem find(int id);
    LineItem find(Product product);
    void remove(int id);
    List<Product> getAll();
    int getItemNumber();
    float totalPrice();

}
