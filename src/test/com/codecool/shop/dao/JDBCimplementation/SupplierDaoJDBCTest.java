package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.SupplierDaoTest;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by dorasztanko on 2016.11.23..
 */
public class SupplierDaoJDBCTest extends SupplierDaoTest {
    @Before
    public void setUp() throws Exception {
        supplierDao = new SupplierDaoJDBC();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void selectAllSQL() throws Exception {

    }

}