package com.codecool.shop.controller;

import com.codecool.shop.model.UserModel;
import com.codecool.shop.service.UserModelService;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;

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
            MailServiceController.sendEmail(userModel.getName(), "welcome", userModel.getEmail());
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.redirect("/");
        return null;
    }
}
