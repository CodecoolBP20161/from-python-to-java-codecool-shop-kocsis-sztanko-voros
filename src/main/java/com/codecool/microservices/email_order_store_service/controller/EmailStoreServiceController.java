package com.codecool.microservices.email_order_store_service.controller;

import com.codecool.microservices.email_order_store_service.dao.inmemoryimplementation.EmailDaoMem;
import com.codecool.microservices.email_order_store_service.service.EmailStoreService;
import spark.Request;
import spark.Response;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;

public class EmailStoreServiceController {
    public static final String SUBJECT_PARAM_KEY = "subject";
    public static final String EMAIL_PARAM_KEY = "email";
    public static final String USERNAME_PARAM_KEY = "username";
    EmailStoreService emailStoreService;

    public EmailStoreServiceController(EmailStoreService emailStoreService) {
        this.emailStoreService = EmailStoreService.getInstance();
    }

    public String storeEmail(Request request, Response response) throws MessagingException, IOException {
        String subject = request.queryParams(SUBJECT_PARAM_KEY);
        String email = request.queryParams(EMAIL_PARAM_KEY);
        String username = request.queryParams(USERNAME_PARAM_KEY);
        emailStoreService.storeEmail(subject, email, username);
        return "Email stored.";
    }

    public String getAllEmail(Request request, Response response) {
        return emailStoreService.getAll();
    }

    public String changeStatus(Request request, Response response) {
        String id = request.queryParams("id");
        emailStoreService.changeEmailStatus(id);
        return "Status changed";
    }
}
