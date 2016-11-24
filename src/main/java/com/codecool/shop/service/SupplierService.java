package com.codecool.shop.service;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierService{
    SupplierDao supplierDao;
    private static SupplierService ourInstance = new SupplierService();

    public static SupplierService getInstance() {
        return ourInstance;
    }

    private SupplierService() {
        this.supplierDao = StorageFactory.setSupplierStorage(Storage.DATABASE);
    }

    public void add(Supplier category) {
        supplierDao.add(category);
    }

    public Supplier find(int id) {
        Supplier supplier = supplierDao.find(id);
        ArrayList<Product> products = new ArrayList(ProductService.getInstance().getBy(supplier));
        supplier.setProducts(products);
        return supplier;
    }

    public void remove(int id) {
        supplierDao.remove(id);
    }

    public List<Supplier> getAll() {
        return supplierDao.getAll();
    }
}
