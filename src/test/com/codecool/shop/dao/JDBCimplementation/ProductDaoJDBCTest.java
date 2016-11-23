package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductDaoTest;
import org.junit.*;

import static org.junit.Assert.*;

public class ProductDaoJDBCTest  extends ProductDaoTest {

    @Before
    public void setUp() throws Exception {
        productDao = new ProductDaoJDBC();
    }

    @Test
    public void selectAllSQL() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
}