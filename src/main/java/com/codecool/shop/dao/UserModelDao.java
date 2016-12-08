package com.codecool.shop.dao;

import com.codecool.shop.model.UserModel;

import java.util.List;

public interface UserModelDao {

    void add(UserModel userModel);

    UserModel find(String email);

    void remove(String id);

    List<UserModel> getAll();
}
