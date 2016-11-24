package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductDao;
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
        return "SELECT * FROM product JOIN supplier ON (product_supplier = supplier_id) JOIN productcategory ON (product_productcategory = productcategory_id)";
    }

    @Override
    protected String addSQL() {
        return "INSERT INTO product (product_id, product_name, product_defaultprice, product_defaultcurrency,product_description, product_productcategory, product_supplier) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    protected String findSQL() {
        return "SELECT * FROM product JOIN supplier ON (product_supplier = supplier_id) JOIN productcategory ON (product_productcategory = productcategory_id) WHERE product_id = ?";
    }

    @Override
    protected String removeSQL() {
        return "DELETE FROM product WHERE product_id = ?";
    }

    protected String getBySupplierSQL() {
        return "SELECT * FROM product JOIN supplier ON (product_supplier = supplier_id) JOIN productcategory ON (product_productcategory = productcategory_id) WHERE product_supplier = ?";
    }

    protected String getByCategorySQL() {
        return "SELECT * FROM product JOIN supplier ON (product_supplier = supplier_id) JOIN productcategory ON (product_productcategory = productcategory_id) WHERE product_productcategory = ?";
    }

    @Override
    public void add(Product product) {
        String query = addSQL();
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
        String query = findSQL();
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

                product = new Product.ProductBuilder(name, price, currency, description, category, supplier)
                .id(id)
                .build();
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

                Product product = new Product.ProductBuilder(name, price, currency, description, category, supplier)
                        .id(id)
                        .build();
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = getBySupplierSQL();
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

                Product product = new Product.ProductBuilder(name, price, currency, description, category, supplier)
                        .id(id)
                        .build();

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
        String query = getByCategorySQL();
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

                Product product = new Product.ProductBuilder(name, price, currency, description, productCategory, supplier)
                        .id(id)
                        .build();

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
