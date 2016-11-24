package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import org.junit.*;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public abstract class ProductCategoryDaoTest {
    protected ProductCategoryDao productCategoryDao;

    protected ProductCategory tablet = new ProductCategory
            .ProductCategoryBuilder("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.")
            .build();

    protected ProductCategory laptop = new ProductCategory
            .ProductCategoryBuilder("Laptop", "Hardware", "A laptop computer, flat mobile computer with a display.")
            .build();

    @Test
    public void addProductCategoryToProductCategoryDao() throws Exception {
        productCategoryDao.add(laptop);
        assertEquals(2, productCategoryDao.getAll().size());
    }

    @Test
    public void findProductCategoryInProductCategoryDao() throws Exception {
        ProductCategory tablet = productCategoryDao.getAll().get(0);
        assertTrue(Objects.equals(tablet.getName(), productCategoryDao.find(tablet.getId()).getName()));
    }

    @Test
    public void getAllProductCategoryOfProductCategoryDao() throws Exception {
        assertEquals(1, productCategoryDao.getAll().size());
    }

    @Test
    public void remove() throws Exception {
        List<ProductCategory> suppliers = productCategoryDao.getAll();
        ProductCategory tablet = suppliers.get(0);
        int before = suppliers.size();
        productCategoryDao.remove(tablet.getId());
        int after = productCategoryDao.getAll().size();
        assertEquals(before - 1, after);
    }
}
