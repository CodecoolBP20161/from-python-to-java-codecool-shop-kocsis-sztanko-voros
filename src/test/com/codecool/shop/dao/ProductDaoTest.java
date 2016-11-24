package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.*;

import java.util.Objects;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertTrue;

public abstract class ProductDaoTest {
    protected ProductDao productDao;
    protected ProductCategoryDao productCategoryDao;
    protected SupplierDao supplierDao;

    protected ProductCategory tablet = new ProductCategory
            .ProductCategoryBuilder("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.")
            .build();

    protected Supplier lenovo = new Supplier.SupplierBuilder("Lenovo", "Computers")
            .build();

    protected Product product_1 = new Product("Lenovo IdeaPad Mix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);

    protected Product product_2 = new Product("test", 0, "HUF", "no_description", tablet, lenovo);


    @Test
    public void addProductToProductDao() throws Exception {
        productDao.add(product_2);
        assertEquals(2, productDao.getAll().size());
    }

    @Test
    public void findProductInProductDao() throws Exception {
        assertTrue(Objects.equals(product_1.getName(), productDao.find(product_1.getId()).getName()));

    }

    @Test
    public void removeProductFromProductDao() throws Exception {

    }

    @Test
    public void getAllProductFromProductDao() throws Exception {

    }

    @Test
    public void getProductBySupplier() throws Exception {

    }

    @Test
    public void getByProductByProductCategory() throws Exception {

    }
}
