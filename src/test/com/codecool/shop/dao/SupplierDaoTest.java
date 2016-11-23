package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import org.junit.*;

import java.util.List;
import java.util.Objects;

import static junit.framework.TestCase.*;

public abstract  class SupplierDaoTest {
    protected SupplierDao supplierDao;

    protected Supplier lenovo = new Supplier.SupplierBuilder("Lenovo", "Computers")
            .build();

    protected Supplier amazon = new Supplier.SupplierBuilder("Amazon", "Digital content and services")
            .build();

    @Test
    public void addSupplierToSupplierDao() throws Exception {
        supplierDao.add(amazon);
        assertEquals(2, supplierDao.getAll().size());
    }

    @Test
    public void findSupplierInSupplierDao() throws Exception {
        Supplier lenovo = supplierDao.getAll().get(0);
        assertTrue(Objects.equals(lenovo.getName(), supplierDao.find(lenovo.getId()).getName()));
    }

    @Test
    public void getAllSupplierOfSupplierDao() throws Exception {
        assertEquals(1, supplierDao.getAll().size());
    }

    @Test
    public void removeFromSupplierDao() throws Exception {
        List<Supplier> suppliers = supplierDao.getAll();
        Supplier lenovo = suppliers.get(0);
        int before = suppliers.size();
        supplierDao.remove(lenovo.getId());
        int after = supplierDao.getAll().size();
        assertEquals(before - 1, after);
    }
}
