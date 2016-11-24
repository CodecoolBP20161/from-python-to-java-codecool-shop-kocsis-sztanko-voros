package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.SupplierDaoTest;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SupplierDaoJDBCTest extends SupplierDaoTest {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "doca1993";

    @Before
    public void setUp() throws Exception {
        supplierDao = new SupplierDaoJDBC();
        supplierDao.add(lenovo);
    }

    @After
    public void tearDown() throws Exception {
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