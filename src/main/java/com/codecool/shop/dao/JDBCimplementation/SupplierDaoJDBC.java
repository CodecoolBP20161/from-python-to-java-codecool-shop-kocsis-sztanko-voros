package com.codecool.shop.dao.JDBCimplementation;


import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
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


public class SupplierDaoJDBC extends DataBaseAbstraction implements SupplierDao {

    @Override
    protected String selectAllSQL() {
        return "SELECT * FROM supplier";
    }

    @Override
    protected String addSQL() {
        return "INSERT INTO supplier (supplier_name, supplier_description) VALUES (?, ?)";
    }

    @Override
    protected String findSQL() {
        return "SELECT * FROM supplier WHERE supplier_id = ?";
    }

    @Override
    protected String removeSQL() {
        return "DELETE * FROM supplier WHERE supplier_id = ?";
    }

    @Override
    public void add(Supplier supplier) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = addSQL();
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getDescription());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(stmt);
        }
    }

    @Override
    public Supplier find(int id) {
        Supplier supplier = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        CachedRowSet rowset;
        String sql = findSQL();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rowset = new CachedRowSetImpl();
            rowset.populate(rs);
            while (rowset.next()){

                supplier = new Supplier
                .SupplierBuilder(rowset.getString("supplier_name"), rowset.getString("supplier_description"))
                .id(rowset.getInt("supplier_id"))
                .build();

                ProductDaoJDBC productDaoJDBC = new ProductDaoJDBC();
                ArrayList<Product> products = new ArrayList((productDaoJDBC.getBy(supplier)));
                supplier.setProducts(products);
                return supplier;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(stmt);
        }
        return supplier;
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> supList = new ArrayList<>();

        RowSet rs = selectAll();
        try {
            ProductDaoJDBC productDaoJDBC = new ProductDaoJDBC();
            while (rs.next()) {

                Supplier supplier = new Supplier
                        .SupplierBuilder(rs.getString("supplier_name"),rs.getString("supplier_description"))
                        .id(rs.getInt("supplier_id"))
                        .build();

                ArrayList<Product> products = new ArrayList((productDaoJDBC.getBy(supplier)));
                supplier.setProducts(products);

                supList.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supList;
    }
}
