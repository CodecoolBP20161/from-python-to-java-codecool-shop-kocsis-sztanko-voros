package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;


public class Supplier extends BaseModel implements Filter {

    /*
    ProductCategory object represents a group of products according to their category
    */

    private ArrayList<Product> products;

    public Supplier(String name, String description) {
        super(name,description);
        this.products = new ArrayList<>();
    }

    public Supplier(SupplierBuilder builder) {
        super(builder.name,builder.description);
        this.id = builder.id;
        this.products = builder.products;
    }

    public static class SupplierBuilder {

        private int id;
        private String name;
        private String description;
        private ArrayList<Product> products;

        public SupplierBuilder name (String name) {
            this.name = name;
            return this;
        }

        public SupplierBuilder description (String description) {
            this.description = description;
            return this;
        }

        public SupplierBuilder id (int id) {
            this.id = id;
            return this;
        }

        public SupplierBuilder products(ArrayList<Product> products) {
            this.products = products;
            return this;
        }

        public Supplier build() {
            return new Supplier(this);
        }

    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public List getBy(){
        return getProducts();
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}