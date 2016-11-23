package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.dbutils.DbUtils.closeQuietly;

public class ProductDaoJDBC extends DataBaseAbstraction implements ProductDao {

    @Override
    protected String selectAllSQL() {
        String query = "SELECT * FROM product JOIN supplier ON (product_supplier = supplier_id) JOIN productcategory ON (product_productcategory = productcategory_id)";
        return query;
    }

    @Override
    public void add(Product product) {
        String query = "INSERT INTO product (product_id, product_name, product_defaultprice, product_defaultcurrency,product_description, product_productcategory, product_supplier) VALUES (?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setFloat(3, product.getDefaultPrice());
            preparedStatement.setString(4, product.getDefaultCurrency().getCurrencyCode());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getProductCategory().getId());
            preparedStatement.setInt(7, product.getSupplier().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(preparedStatement);
        }
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM product JOIN supplier ON (product_supplier = supplier_id) JOIN productcategory ON (product_productcategory = productcategory_id) WHERE id = ?";
        Product product = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("product_name");
                String description = rs.getString("product_description");
                Float price = rs.getFloat("product_defaultprice");
                String currency = rs.getString("product_defaultcurrency");
                ProductCategory category = new ProductCategory.ProductCategoryBuilder(rs.getString("productcategory_name"), rs.getString("productcategory_department"), rs.getString("productcategory_description"))
                        .id(rs.getInt("productcategory_id"))
                        .build();
                Supplier supplier = new Supplier.SupplierBuilder(rs.getString("supplier_name"),rs.getString("supplier_description"))
                        .build();
                product = new Product(id, name, price, currency, description, category, supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(preparedStatement);
        }
        return product;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM product WHERE product_id = ?";
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
    public List<Product> getAll() {
        RowSet rs = selectAll();
        List<Product> productList= new ArrayList<>();
        try {
            while (rs.next()) {
                Integer id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                String description = rs.getString("product_description");
                Float price = rs.getFloat("product_defaultprice");
                String currency = rs.getString("product_defaultcurrency");
                ProductCategory category = new ProductCategory.ProductCategoryBuilder(rs.getString("productcategory_name"), rs.getString("productcategory_department"), rs.getString("productcategory_description"))
                        .id(rs.getInt("productcategory_id"))
                        .build();
                Supplier supplier = new Supplier.SupplierBuilder(rs.getString("supplier_name"),rs.getString("supplier_description"))
                        .build();
                Product product = new Product(id, name, price, currency, description, category, supplier);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM product JOIN supplier ON (product_supplier = supplier_id) JOIN productcategory ON (product_productcategory = productcategory_id) WHERE product_supplier = ?";
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, supplier.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                String description = rs.getString("product_description");
                Float price = rs.getFloat("product_defaultprice");
                String currency = rs.getString("product_defaultcurrency");
                ProductCategory category = new ProductCategory.ProductCategoryBuilder(rs.getString("productcategory_name"), rs.getString("productcategory_department"), rs.getString("productcategory_description"))
                        .id(rs.getInt("productcategory_id"))
                        .build();
                Product product = new Product(id, name, price, currency, description, category, supplier);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(preparedStatement);
        }
        return productList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM product JOIN supplier ON (product_supplier = supplier_id) JOIN productcategory ON (product_productcategory = productcategory_id) WHERE product_productcategory = ?";
        List<Product> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productCategory.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                String description = rs.getString("product_description");
                Float price = rs.getFloat("product_defaultprice");
                String currency = rs.getString("product_defaultcurrency");
                Supplier supplier = new Supplier.SupplierBuilder(rs.getString("supplier_name"),rs.getString("supplier_description"))
                        .build();
                Product product = new Product(id, name, price, currency, description, productCategory, supplier);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(preparedStatement);
        }
        return productList;
    }
}
