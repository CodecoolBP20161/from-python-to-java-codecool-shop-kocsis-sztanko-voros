package com.codecool.shop.controller;

import com.codecool.shop.model.UserModel;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class RegistrationHandler {

    public static String handleRegistration(Request req, Response res) {
        JSONObject json = new JSONObject(req.body());

        String salt;
        salt = SecurityHandler.createSalt();
        new UserModel.UserBuilder(json.getString("name"),
                              json.getString("email"),
                              salt,
                              SecurityHandler.createHashedPassword(json.getString("password"), salt)).build();
        return null;
    }
}
