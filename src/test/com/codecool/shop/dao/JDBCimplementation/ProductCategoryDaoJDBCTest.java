package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductCategoryDaoTest;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class ProductCategoryDaoJDBCTest extends ProductCategoryDaoTest {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "doca1993";

    @Before
    public void setUp() throws Exception {
        productCategoryDao = new ProductCategoryDaoJDBC();
        productCategoryDao.add(tablet);
    }

//    @Test(expected = SQLException.class)
//    public void badConnectionCredentials() {
//        // ???
//    }

    @After
    public void tearDown() throws Exception {
        executeQuery("DELETE FROM productcategory");
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