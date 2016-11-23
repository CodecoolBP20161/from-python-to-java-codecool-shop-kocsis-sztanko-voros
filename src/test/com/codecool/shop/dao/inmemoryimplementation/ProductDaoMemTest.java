package com.codecool.shop.dao.inmemoryimplementation;

import com.codecool.shop.dao.ProductDaoTest;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by dorasztanko on 2016.11.23..
 */
public class ProductDaoMemTest extends ProductDaoTest {

    @Before
    public void setUp() throws Exception {
        productDao = ProductDaoMem.getInstance();
    }

    @Test
    public void getInstance() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


}