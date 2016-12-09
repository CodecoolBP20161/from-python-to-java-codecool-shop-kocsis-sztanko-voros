package com.codecool.shop.service;

import com.codecool.shop.dao.UserModelDao;
import com.codecool.shop.model.UserModel;

import java.util.List;

public class UserModelService {
    UserModelDao userModelDao;
    private static UserModelService ourInstance = new UserModelService();

    public static UserModelService getInstance() {
        return ourInstance;
    }

    private UserModelService() {
        this.userModelDao = StorageFactory.setUserModelStorage(Storage.DATABASE);
    }

    public void add(UserModel userModel) {
        userModelDao.add(userModel);
    }

    public UserModel find(String email) {
        return userModelDao.find(email);
    }

    public void remove(String email) {
        userModelDao.remove(email);
    }

    public List<UserModel> getAll() {
        return userModelDao.getAll();
    }
}
