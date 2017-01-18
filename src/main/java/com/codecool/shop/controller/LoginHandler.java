package com.codecool.shop.controller;


import com.codecool.shop.model.UserModel;
import com.codecool.shop.service.UserModelService;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class LoginHandler {

    public static String handleLogin(Request req, Response res) {
        UserModelService userModelDataStore = UserModelService.getInstance();
        JSONObject json = new JSONObject(req.body());

        if (userModelDataStore.find(json.getString("email")) == null) {
            return "ERROR";
        }
        UserModel user = userModelDataStore.find(json.getString("email"));
        String enteredPassword = SecurityHandler.createHashedPassword(json.getString("password"), user.getPasswordSalt());
        if (!enteredPassword.equals(user.getPasswordHash())) {
            return "ERROR";
        }
        SessionHandler.logIn(req, res);
        return "OK";
    }

    public static String handleLogout(Request req, Response res) {
        SessionHandler.logOut(req, res);
        return "OK";
    }
}

