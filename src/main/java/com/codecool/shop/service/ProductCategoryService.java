package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductCategoryService{
    ProductCategoryDao productCategoryDao;
    private static ProductCategoryService ourInstance = new ProductCategoryService();

    public static ProductCategoryService getInstance() {
        return ourInstance;
    }

    private ProductCategoryService() {
        this.productCategoryDao = StorageFactory.setProductCategoryStorage(Storage.DATABASE);
    }

    public void add(ProductCategory category) {
        productCategoryDao.add(category);
    }

    public ProductCategory find(int id) {
        return productCategoryDao.find(id);
    }

    public void remove(int id) {
        productCategoryDao.remove(id);
    }

    public List<ProductCategory> getAll() {
        return productCategoryDao.getAll();
    }
}
