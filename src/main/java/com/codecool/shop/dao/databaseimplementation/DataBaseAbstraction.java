package com.codecool.shop.dao.databaseimplementation;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import java.sql.*;

import static org.apache.commons.dbutils.DbUtils.closeQuietly;

public abstract class DataBaseAbstraction {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";


    abstract String createSQL();

    protected abstract String selectAllSQL();


    protected Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void createTable() {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = createSQL();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(stmt);
            closeQuietly(conn);
        }

    }

    public RowSet selectAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CachedRowSet rowset = null;

        String sql = selectAllSQL();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            rowset = new CachedRowSetImpl();
            rowset.populate(rs);
            return rowset;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(stmt);
            closeQuietly(rs);
        }
        return rowset;
    }

}
