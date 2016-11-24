package com.codecool.shop.dao.inmemoryimplementation;

import com.codecool.shop.dao.ProductDaoTest;
import org.junit.*;

import static org.junit.Assert.*;

public class ProductDaoMemTest extends ProductDaoTest {

    @Before
    public void setUp() throws Exception {
        productDao = ProductDaoMem.getInstance();
        productDao.add(product_1);
    }

    @After
    public void tearDown() throws Exception {
        productDao.getAll().clear();

    }


}