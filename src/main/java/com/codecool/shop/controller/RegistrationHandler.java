package com.codecool.shop.controller;

import com.codecool.shop.model.UserModel;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class RegistrationHandler {

    public static String handleRegistration(Request req, Response res) {
        String salt;
        System.out.println(req.body());
        System.out.println(req.queryParams("name"));
//        JSONObject json = new JSONObject(req.body());
//        System.out.println(json.getString("name"));
//        salt = SecurityHandler.createSalt();
//        new UserModel.UserBuilder(req.queryParams("Username"),
//                                  req.queryParams("Email"),
//                                  salt,
//                                  SecurityHandler.createHashedPassword(req.queryParams("Password"), salt)).build();
        res.redirect("/");
        return null;
    }
}
