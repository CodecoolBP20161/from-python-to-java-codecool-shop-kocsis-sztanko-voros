package com.codecool.shop.dao.JDBCimplementation;

import com.codecool.shop.dao.UserModelDao;
import com.codecool.shop.model.UserModel;

import java.util.List;

public class UserModelDaoJDBC extends DataBaseAbstraction implements UserModelDao{

    @Override
    protected String selectAllSQL() {
        return "SELECT * FROM user_model";
    }

    @Override
    protected String addSQL() {
        return "INSERT INTO user_model (user_id, user_name, user_email, user_passwordhash, user_passwordsalt) VALUES (?,?,?,?,?)";
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

    }

    @Override
    public UserModel find(String email) {
        return null;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<UserModel> getAll() {
        return null;
    }

}
