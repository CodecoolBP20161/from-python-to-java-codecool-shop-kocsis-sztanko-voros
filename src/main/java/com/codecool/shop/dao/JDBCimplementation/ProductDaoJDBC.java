package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.RowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC extends DataBaseAbstraction implements ProductDao {

    @Override
    protected String selectAllSQL() {
        String query = "SELECT * FROM product";
        return query;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        RowSet rs = selectAll();
        List<Product> productList= new ArrayList<>();
        try {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Float price = rs.getFloat("defaultprice");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
