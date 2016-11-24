package com.codecool.shop.service;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class ProductService {
    private ProductDao productDao;
    private static ProductService ourInstance = new ProductService();

    public static ProductService getInstance() {
        return ourInstance;
    }

    private ProductService() {
        this.productDao = StorageFactory.setProductStorage(Storage.DATABASE);
    }

    public void add(Product product) {
        productDao.add(product);
    }

    public Product find(int id) {
        return productDao.find(id);
    }

    public void remove(int id) {
        productDao.remove(id);
    }

    public List<Product> getAll() {
        return productDao.getAll();
    }

    public List<Product> getBy(Supplier supplier) {
        return productDao.getBy(supplier);
    }

    public List<Product> getBy(ProductCategory productCategory) {
        return productDao.getBy(productCategory);
    }

}
