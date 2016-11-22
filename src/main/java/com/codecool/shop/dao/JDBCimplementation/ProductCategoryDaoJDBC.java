package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

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

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
