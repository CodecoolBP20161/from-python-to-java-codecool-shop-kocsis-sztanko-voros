package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.SupplierDaoTest;
import org.junit.*;

/**
 * Created by dorasztanko on 2016.11.23..
 */
public class SupplierDaoJDBCTest extends SupplierDaoTest {

    @Before
    public void setUp() throws Exception {
        supplierDao = new SupplierDaoJDBC();
    }

    @Test
    public void selectAllSQL() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        supplierDao = null;
    }
}