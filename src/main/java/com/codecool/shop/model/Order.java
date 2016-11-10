package com.codecool.shop.model;


import java.util.ArrayList;

    /*
    Order object is created after Shopping cart content is checked --- not used yet
     */

public class Order {
    private int id;
    private String status;
    private ArrayList<LineItem> items;

    public Order(String status) {
        this.status = status;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<LineItem> items) {
        this.items = items;
    }
}
