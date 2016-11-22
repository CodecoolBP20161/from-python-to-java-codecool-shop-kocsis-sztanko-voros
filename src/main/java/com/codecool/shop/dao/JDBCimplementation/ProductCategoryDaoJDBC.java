package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.RowSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        System.out.println(listOfProductCategories);
        return listOfProductCategories;
    }
}
