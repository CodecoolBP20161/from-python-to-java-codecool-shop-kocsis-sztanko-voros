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
        List<ProductCategory> categories = productCategoryDao.getAll();
        for (int i = 0; i < categories.size(); i++) {
            categories.remove(categories.get(i));
        }
    }
}