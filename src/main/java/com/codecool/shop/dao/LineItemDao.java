package com.codecool.shop.dao;


import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import java.util.List;

public interface LineItemDao {

    void add(Product product);
    LineItem find(int id);
    LineItem find(Product product);
    void remove(int id);
    List<Product> getAll();
    int getItemNumber();
    float totalPrice();

}
