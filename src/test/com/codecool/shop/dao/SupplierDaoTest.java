package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import org.junit.*;

import static junit.framework.TestCase.*;

/**
 * Created by dorasztanko on 2016.11.23..
 */
public abstract  class SupplierDaoTest {
    protected SupplierDao supplierDao;

    private Supplier lenovo = new Supplier.SupplierBuilder("Lenovo", "Computers")
            .build();

    @Test
    public void add() throws Exception {
        supplierDao.add(lenovo);
        assertEquals(1, supplierDao.getAll().size());
    }

    @Test
    public void find() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }
}
