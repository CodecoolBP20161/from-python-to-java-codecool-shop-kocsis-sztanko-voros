package com.codecool.shop.dao;

import com.codecool.shop.dao.JDBCimplementation.ProductDaoJDBC;
import com.codecool.shop.dao.inmemoryimplementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.*;

import static junit.framework.TestCase.*;

/**
 * Created by dorasztanko on 2016.11.23..
 */
public abstract class ProductDaoTest {
    protected ProductDao productDao;

    private ProductCategory tablet = new ProductCategory
            .ProductCategoryBuilder("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.")
            .build();

    private Supplier lenovo = new Supplier.SupplierBuilder("Lenovo", "Computers")
            .build();

    private Product product = new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);

    @Test
    public void addProductToProductDao() throws Exception {
        productDao.add(product);
        assertEquals(1, productDao.getAll().size());
    }

    @Test
    public void findProductInProductDao() throws Exception {

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
