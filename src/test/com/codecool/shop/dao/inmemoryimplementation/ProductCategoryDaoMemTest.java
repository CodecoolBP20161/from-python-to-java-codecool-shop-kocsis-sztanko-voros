package com.codecool.shop.dao.inmemoryimplementation;

import com.codecool.shop.dao.ProductCategoryDaoTest;
import com.codecool.shop.model.ProductCategory;
import org.junit.*;

import java.util.List;

public class ProductCategoryDaoMemTest extends ProductCategoryDaoTest {

    @Before
    public void setUp() throws Exception {
        productCategoryDao = ProductCategoryDaoMem.getInstance();
        productCategoryDao.add(tablet);
    }

    @After
    public void tearDown() throws Exception {
        productCategoryDao.getAll().clear();
    }
}
