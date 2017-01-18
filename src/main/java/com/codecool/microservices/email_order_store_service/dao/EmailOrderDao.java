package com.codecool.microservices.email_order_store_service.dao;

import com.codecool.microservices.email_order_store_service.model.EmailOrder;

import java.util.ArrayList;

public interface EmailOrderDao {

    void add(EmailOrder emailOrder);

    EmailOrder find(String id);

    void remove(String id);

    void changeStatus(String id);

    ArrayList getAllNew();

}
