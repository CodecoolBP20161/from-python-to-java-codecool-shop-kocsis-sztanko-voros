package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
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

    protected Product product_1 = new Product.ProductBuilder("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo).build();

    protected Product product_2 = new Product.ProductBuilder("test", 0, "HUF", "no_description", tablet, lenovo).build();


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
        List<Product> products = productDao.getAll();
        Product product_1 = products.get(0);
        int before = products.size();
        productDao.remove(product_1.getId());
        int after = productDao.getAll().size();
        assertEquals(before - 1, after);
    }

    @Test
    public void getAllProductFromProductDao() throws Exception {
        assertEquals(1, productDao.getAll().size());
    }

    @Test
    public void getProductBySupplier() throws Exception {
        List<Product> filteredList = new ArrayList<>();
        filteredList.add(product_1);
        assertEquals(filteredList.size(), productDao.getBy(lenovo).size());
    }

    @Test
    public void getByProductByProductCategory() throws Exception {
        List<Product> filteredList = new ArrayList<>();
        filteredList.add(product_1);
        assertEquals(filteredList.size(), productDao.getBy(tablet).size());
    }
}
