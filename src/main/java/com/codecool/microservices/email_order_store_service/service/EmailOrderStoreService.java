package com.codecool.microservices.email_order_store_service.service;

import com.codecool.microservices.email_order_store_service.dao.EmailOrderDao;
import com.codecool.microservices.email_order_store_service.dao.inmemoryimplementation.EmailOrderDaoMem;
import com.codecool.microservices.email_order_store_service.model.EmailOrder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class EmailOrderStoreService {
    private static EmailOrderStoreService ourInstance = new EmailOrderStoreService();

    public static EmailOrderStoreService getInstance() {
        return ourInstance;
    }

    private static EmailOrderDao emailOrderDao = EmailOrderDaoMem.getInstance();

    public void storeEmail(String subject, String email, String username) throws IOException {
        EmailOrder emailOrder = new EmailOrder(subject, email, username);
        emailOrderDao.add(emailOrder);
    }

    public void changeEmailStatus(String id) {
        emailOrderDao.changeStatus(id);
    }

    public String getAllNewEmail() {
        JSONArray jsonArray = new JSONArray(emailOrderDao.getAllNew());
        JSONObject json = new JSONObject();
        json.put("emails", jsonArray);
        return json.toString();
    }
}
