package com.codecool.microservices.email_order_store_service.dao;

import com.codecool.microservices.email_order_store_service.model.UserEmail;

import java.util.ArrayList;

public interface EmailDao {

    void add(UserEmail userEmail);
    UserEmail find(String id);
    void remove(String id);
    void changeStatus(String id);
    ArrayList getAll();

}
