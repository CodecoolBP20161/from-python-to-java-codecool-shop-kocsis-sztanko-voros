package com.codecool.shop.service;

import com.codecool.shop.dao.JDBCimplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.JDBCimplementation.ProductDaoJDBC;
import com.codecool.shop.dao.JDBCimplementation.SupplierDaoJDBC;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.inmemoryimplementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.inmemoryimplementation.ProductDaoMem;
import com.codecool.shop.dao.inmemoryimplementation.SupplierDaoMem;

public class StorageFactory {
    public static ProductDao setProductStorage(Storage storage) {
        switch (storage) {
            case DATABASE:
                return new ProductDaoJDBC();
            case MEMORY:
                return ProductDaoMem.getInstance();
            default:
                return null;
        }
    }

    public static SupplierDao setSupplierStorage(Storage storage) {
        switch (storage) {
            case DATABASE:
                return new SupplierDaoJDBC();
            case MEMORY:
                return SupplierDaoMem.getInstance();
            default:
                return null;
        }
    }

    public static ProductCategoryDao setProductCategoryStorage(Storage storage) {
        switch (storage) {
            case DATABASE:
                return new ProductCategoryDaoJDBC();
            case MEMORY:
                return ProductCategoryDaoMem.getInstance();
            default:
                return null;
        }
    }
}
