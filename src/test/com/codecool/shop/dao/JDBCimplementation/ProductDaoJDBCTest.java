package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoTest;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by dorasztanko on 2016.11.23..
 */
public class ProductDaoJDBCTest  extends ProductDaoTest {

    @Before
    public void setUp() throws Exception {
        productDao = new ProductDaoJDBC();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void selectAllSQL() throws Exception {

    }
}