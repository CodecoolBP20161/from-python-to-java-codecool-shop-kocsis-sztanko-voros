package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.dbutils.DbUtils.closeQuietly;

/**
 * Created by dorasztanko on 2016.11.22..
 */
public class ProductCategoryDaoJDBC extends DataBaseAbstraction implements ProductCategoryDao {

    @Override
    protected String selectAllSQL() {
        String query = "SELECT * FROM productcategory";
        return query;
    }

    @Override
    public void add(ProductCategory category) {
        String query = "INSERT INTO productcategory (name, department, description) VALUES (?,?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDepartment());
            preparedStatement.setString(3, category.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(preparedStatement);
        }
    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM productcategory WHERE id = ?";
        ProductCategory productCategory = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            ProductDaoJDBC productDaoJDBC= new ProductDaoJDBC();
            ArrayList<Product> products;

            while (rs.next()) {
            productCategory = new ProductCategory(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            products = (ArrayList) productDaoJDBC.getBy(productCategory);
            productCategory.setProducts(products);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(preparedStatement);
        }
        return productCategory;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM productcategory WHERE id = ?";
        PreparedStatement preparedStatement = null;
        Connection conn = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(preparedStatement);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        ArrayList<ProductCategory> listOfProductCategories = new ArrayList<>();
        RowSet rs = selectAll();
        try {
            while (rs.next()) {
                ProductCategory productCategory = new ProductCategory(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                listOfProductCategories.add(productCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfProductCategories;
    }
}
