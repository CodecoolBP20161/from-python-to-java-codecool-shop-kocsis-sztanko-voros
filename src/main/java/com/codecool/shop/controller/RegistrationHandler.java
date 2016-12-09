package com.codecool.shop.controller;

import com.codecool.shop.model.UserModel;
import com.codecool.shop.service.UserModelService;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.io.IOException;

public class RegistrationHandler {

    public static String handleRegistration(Request req, Response res) {
        UserModelService userModelDataStore = UserModelService.getInstance();
        String salt;
        JSONObject json = new JSONObject(req.body());
        if (userModelDataStore.find(json.getString("email")) != null){
            return "NO";
        }
        salt = SecurityHandler.createSalt();
        UserModel userModel = new UserModel.UserBuilder(json.getString("name"),
                                  json.getString("email"),
                                  SecurityHandler.createHashedPassword(json.getString("password"), salt),
                                  salt).build();
        userModelDataStore.add(userModel);
        try {
            EmailServiceController.sendEmail(userModel.getName(), "welcome", userModel.getEmail());
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.header("Content-Type:application/json","{status:OK}");
        return "OK";
    }
}
