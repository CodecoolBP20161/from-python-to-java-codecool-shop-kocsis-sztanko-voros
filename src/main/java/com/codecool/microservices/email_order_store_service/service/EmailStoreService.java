package com.codecool.microservices.email_order_store_service.service;

import com.codecool.microservices.email_order_store_service.dao.EmailDao;
import com.codecool.microservices.email_order_store_service.dao.inmemoryimplementation.EmailDaoMem;
import com.codecool.microservices.email_order_store_service.model.UserEmail;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class EmailStoreService {
    private static EmailStoreService ourInstance = new EmailStoreService();

    public static EmailStoreService getInstance() {
        return ourInstance;
    }

    private static EmailDao emailDao = EmailDaoMem.getInstance();

    public void storeEmail(String subject, String email, String username) throws IOException {
        UserEmail userEmail = new UserEmail(subject, email, username);
        emailDao.add(userEmail);
    }

    public void removeEmail(String id) {
        emailDao.remove(id);
    }

    public void changeEmailStatus(String id) {
        emailDao.changeStatus(id);
    }

    public String getAll() {
        JSONArray jsonArray = new JSONArray(emailDao.getAll());
        JSONObject json = new JSONObject();
        json.put("emails", jsonArray);
        return json.toString();
    }
}
