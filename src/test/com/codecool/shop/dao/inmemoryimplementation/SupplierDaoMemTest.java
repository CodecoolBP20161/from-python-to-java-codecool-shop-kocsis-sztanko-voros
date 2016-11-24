package com.codecool.shop.dao.inmemoryimplementation;

import com.codecool.shop.dao.SupplierDaoTest;
import org.junit.*;

public class SupplierDaoMemTest extends SupplierDaoTest {

    @Before
    public void setUp() throws Exception {
        supplierDao = SupplierDaoMem.getInstance();
        supplierDao.add(lenovo);
    }

    @After
    public void tearDown() throws Exception {
        supplierDao.getAll().clear();

    }
}