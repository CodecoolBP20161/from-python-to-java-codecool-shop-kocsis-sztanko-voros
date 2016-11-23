package com.codecool.shop.dao.inmemoryimplementation;

import com.codecool.shop.dao.SupplierDaoTest;
import com.codecool.shop.model.Supplier;
import org.junit.*;

import java.util.List;

public class SupplierDaoMemTest extends SupplierDaoTest {

    @Before
    public void setUp() throws Exception {
        supplierDao = SupplierDaoMem.getInstance();
        supplierDao.add(lenovo);
        System.out.println(supplierDao.getAll());
    }

    @After
    public void tearDown() throws Exception {
        List<Supplier> suppliers = supplierDao.getAll();
        System.out.println(suppliers.size());
        for (int i = 0; i < suppliers.size(); i++) {
            System.out.println(suppliers.get(i));
            suppliers.remove(suppliers.get(i));
        }
        System.out.println(suppliers);
    }
}