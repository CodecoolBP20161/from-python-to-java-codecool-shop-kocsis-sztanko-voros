package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.sun.rowset.CachedRowSetImpl;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
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
        return "SELECT * FROM productcategory";
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
        } catch (Exception e) {
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
        ResultSet rs;
        CachedRowSet rowset;

        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            rowset = new CachedRowSetImpl();
            rowset.populate(rs);

            while (rowset.next()) {

                productCategory = new ProductCategory
                        .ProductCategoryBuilder(rowset.getString("name"), rowset.getString("description"), rowset.getString("department"))
                        .id(rowset.getInt("id"))
                        .build();

                return productCategory;
            }

        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(preparedStatement);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> listOfProductCategories = new ArrayList<>();
        RowSet rs = selectAll();
        try {
            ProductDaoJDBC productDaoJDBC = new ProductDaoJDBC();
            while (rs.next()) {

                ProductCategory productCategory = new ProductCategory
                        .ProductCategoryBuilder(rs.getString("name"), rs.getString("description"), rs.getString("department"))
                        .id(rs.getInt("id"))
                        .build();

                ArrayList<Product> products = new ArrayList((productDaoJDBC.getBy(productCategory)));
                productCategory.setProducts(products);
                listOfProductCategories.add(productCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfProductCategories;
    }
}