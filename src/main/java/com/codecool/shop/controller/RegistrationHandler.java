package com.codecool.shop.controller;

import com.codecool.shop.model.UserModel;
import com.codecool.shop.service.MailService;
import com.codecool.shop.service.UserModelService;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import javax.mail.MessagingException;

public class RegistrationHandler {

    public static String handleRegistration(Request req, Response res) {
        UserModelService userModelDataStore = UserModelService.getInstance();
        String salt;
        JSONObject json = new JSONObject(req.body());
        salt = SecurityHandler.createSalt();
        UserModel userModel = new UserModel.UserBuilder(json.getString("name"),
                                  json.getString("email"),
                                  salt,
                                  SecurityHandler.createHashedPassword(json.getString("password"), salt)).build();
        userModelDataStore.add(userModel);
        try {
            MailService.sendMail("....", "Welcome at Codecool Shop!", userModel.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        res.redirect("/");
        return null;
    }
}
