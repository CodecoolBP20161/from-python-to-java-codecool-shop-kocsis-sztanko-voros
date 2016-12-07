package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.UserModelDao;
import com.codecool.shop.model.UserModel;
import com.sun.rowset.CachedRowSetImpl;

import javax.jws.soap.SOAPBinding;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.dbutils.DbUtils.close;
import static org.apache.commons.dbutils.DbUtils.closeQuietly;

public class UserModelDaoJDBC extends DataBaseAbstraction implements UserModelDao{

    @Override
    protected String selectAllSQL() {
        return "SELECT * FROM user_model";
    }

    @Override
    protected String addSQL() {
        return "INSERT INTO user_model (user_name, user_email, user_passwordhash, user_passwordsalt) VALUES (?,?,?,?)";
    }

    @Override
    protected String findSQL() {
        return "SELECT * FROM user_model WHERE user_email = ?";
    }

    @Override
    protected String removeSQL() {
        return "DELETE FROM user_model WHERE user_email = ?";
    }

    @Override
    public void add(UserModel userModel) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String sql = addSQL();
        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userModel.getName());
            preparedStatement.setString(2, userModel.getEmail());
            preparedStatement.setString(3, userModel.getPasswordHash());
            preparedStatement.setString(4, userModel.getPasswordSalt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(preparedStatement);
        }
    }

    @Override
    public UserModel find(String email) {
        UserModel userModel = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        CachedRowSet rowset;
        String sql = findSQL();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            rowset = new CachedRowSetImpl();
            rowset.populate(rs);
            while (rowset.next()){

                userModel = new UserModel
                        .UserBuilder(rowset.getString("user_name"), rowset.getString("user_email"), rowset.getString("user_passwordhash"), rowset.getString("user_passwordsalt"))
                        .build();

                return userModel;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(conn);
            closeQuietly(stmt);
        }
        return userModel;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<UserModel> getAll() {
        List<UserModel> users = new ArrayList<>();

        RowSet rs = selectAll();
        try {
            while (rs.next()) {
                UserModel userModel = new UserModel.UserBuilder(rs.getString("user_name"), rs.getString("user_email"), rs.getString("user_passwordhash"), rs.getString("user_passwordsalt")).build();
                users.add(userModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
