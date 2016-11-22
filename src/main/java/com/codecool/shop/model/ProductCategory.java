package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory extends BaseModel implements Filter {

    /*
    ProductCategory object represents a group of products according to their category
     */

    private String department;
    private ArrayList<Product> products;

    private ProductCategory(ProductCategoryBuilder builder) {
        super(builder.name, builder.description);
        this.department = builder.department;
        this.setId(builder.id);
        this.products = builder.products;
    }

    public static class ProductCategoryBuilder {
        private int id;
        private String name;
        private String description;
        private String department;
        private ArrayList<Product> products;

        public ProductCategoryBuilder (String name, String description, String department) {
            this.name = name;
            this.description = description;
            this.department = department;
            this.products = new ArrayList<>();
        }

        public ProductCategoryBuilder id (int id) {
            this.id = id;
            return this;
        }

        public ProductCategoryBuilder products(ArrayList<Product> products) {
            this.products = products;
            return this;
        }

        public ProductCategory build() {
            return new ProductCategory(this);
        }

    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}