package com.codecool.shop.model;


public class LineItem {

    /*
    LineItem object represents an item in the user's shopping cart
     */

    private int id;
    private int quantity;
    private Product product;
    private double subTotal;

    public LineItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.subTotal = Math.round(this.getQuantity() * this.getProduct().getDefaultPrice() * 10.0) / 10.0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void refreshSubTotal() {
        this.setSubTotal(Math.round(this.getQuantity() * this.getProduct().getDefaultPrice() * 10.0) / 10.0);
    }
}
