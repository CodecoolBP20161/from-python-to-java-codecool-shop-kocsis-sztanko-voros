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

    public SupplierDaoJDBC() {
    }

    @Override
    public void add(Supplier supplier) {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO supplier ("
                + " name," +
                " description" +
                ")VALUES ("
                + " ?, ?)";

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


        String sql = "SELECT * FROM supplier" +
                " WHERE id = ?";

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rowset = new CachedRowSetImpl();
            rowset.populate(rs);
            while (rowset.next()){

                supplier = new Supplier
                        .SupplierBuilder(rowset.getString("name"), rowset.getString("description"))
                        .id(rowset.getInt("id"))
                        .build();

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
    public void remove(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM supplier" +
                " WHERE id = ?";

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(stmt);
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> supList = new ArrayList<>();

        RowSet rs = selectAll();
        try {
            ProductDaoJDBC productDaoJDBC = new ProductDaoJDBC();
            while (rs.next()) {

                Supplier supplier = new Supplier
                        .SupplierBuilder(rs.getString("name"),rs.getString("description"))
                        .id(rs.getInt("id"))
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

    @Override
    protected String selectAllSQL() {
        return "SELECT * FROM supplier";
    }
}
