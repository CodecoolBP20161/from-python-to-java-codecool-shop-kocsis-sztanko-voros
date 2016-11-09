package com.codecool.shop.model;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<LineItem> items;

    public ShoppingCart(){
        this.items = new ArrayList<>();
    }


    public ArrayList<LineItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<LineItem> items) {
        this.items = items;
    }
}
