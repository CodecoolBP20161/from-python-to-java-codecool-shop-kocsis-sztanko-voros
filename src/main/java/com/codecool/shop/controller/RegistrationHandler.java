package com.codecool.shop.controller;

import com.codecool.shop.model.UserModel;
import spark.Request;
import spark.Response;

public class RegistrationHandler {

    public static String handleRegistration(Request req, Response res) {
        String salt;
        salt = SecurityHandler.createSalt();
        new UserModel.UserBuilder(req.queryParams("Username"),
                                  req.queryParams("Email"),
                                  salt,
                                  SecurityHandler.createHashedPassword(req.queryParams("Password"), salt)).build();
        res.redirect("/");
        return null;
    }
}
