package com.codecool.shop.test;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.inmemoryimplementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.inmemoryimplementation.ProductDaoMem;
import com.codecool.shop.dao.inmemoryimplementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

public class TestData {
    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier.SupplierBuilder("Amazon", "Digital content and services")
                .build();
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier.SupplierBuilder("Lenovo", "Computers")
                .build();
        supplierDataStore.add(lenovo);
        Supplier hp = new Supplier.SupplierBuilder("Hewlett-Packard", "Computers")
                .build();
        supplierDataStore.add(hp);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A laptop computer, flat mobile computer with a display.");

        productCategoryDataStore.add(tablet);
        productCategoryDataStore.add(laptop);

        //setting up products tablet
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        //laptop
        productDataStore.add(new Product("Amazon Laptop", 111.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", laptop, amazon));
        productDataStore.add(new Product("Lenovo ThinkPad x220", 999, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", laptop, lenovo));
        productDataStore.add(new Product("HP ProBook", 213, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", laptop, hp));
    }
}
