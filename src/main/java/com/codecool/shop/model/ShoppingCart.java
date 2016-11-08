package com.codecool.shop.model;

import java.util.ArrayList;

public class ShoppingCart {
    private int id;
    private ArrayList<LineItem> items;

    public ShoppingCart(){
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<LineItem> items) {
        this.items = items;
    }
}
