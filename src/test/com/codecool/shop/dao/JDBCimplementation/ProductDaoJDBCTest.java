package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductDaoTest;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDaoJDBCTest  extends ProductDaoTest {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    @Before
    public void setUp() throws Exception {
        productDao = new ProductDaoJDBC();
        supplierDao = new SupplierDaoJDBC();
        productCategoryDao = new ProductCategoryDaoJDBC();

        supplierDao.add(lenovo);
        productCategoryDao.add(tablet);

        tablet = productCategoryDao.getAll().get(0);
        lenovo = supplierDao.getAll().get(0);

        product_1.setProductCategory(tablet);
        product_1.setSupplier(lenovo);
        product_1.setId(1);

        productDao.add(product_1);

        product_2.setProductCategory(tablet);
        product_2.setSupplier(lenovo);
        product_2.setId(2);
    }

    @After
    public void tearDown() throws Exception {
        executeQuery("DELETE FROM product");
        executeQuery("DELETE FROM productcategory");
        executeQuery("DELETE FROM supplier");
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement()
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}