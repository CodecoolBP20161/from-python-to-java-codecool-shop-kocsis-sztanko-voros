package com.codecool.shop.dao.JDBCimplementation;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import java.sql.*;

import static org.apache.commons.dbutils.DbUtils.closeQuietly;

public abstract class DataBaseAbstraction {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";


    protected abstract String selectAllSQL();

    protected abstract String addSQL();

    protected abstract String findSQL();

    protected abstract String removeSQL();

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

    public void remove(int id) {
        String query = removeSQL();
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
}