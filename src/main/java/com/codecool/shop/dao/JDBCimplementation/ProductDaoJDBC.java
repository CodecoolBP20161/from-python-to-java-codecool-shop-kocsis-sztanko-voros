package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.RowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class ProductDaoJDBC extends DataBaseAbstraction implements ProductDao {
    SupplierDao supplierDaoJDBC;
    ProductCategoryDao categoryDaoJDBC;

    public ProductDaoJDBC() {
        supplierDaoJDBC = new SupplierDaoJDBC();
        categoryDaoJDBC = new ProductCategoryDaoJDBC();
    }

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
                String name = rs.getString("name");
                String description = rs.getString("description");
                Float price = rs.getFloat("defaultprice");
                String currency = rs.getString("defaultcurrency");
                Integer categoryID = rs.getInt("product_category");
                ProductCategory category = categoryDaoJDBC.find(categoryID);
                Integer supplierID = rs.getInt("supplier");
                Supplier supplier = supplierDaoJDBC.find(supplierID);
                Product product = new Product(id, name, price, currency, description, category, supplier);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return productList;
        }
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
