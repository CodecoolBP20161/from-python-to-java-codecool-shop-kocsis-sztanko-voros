package com.codecool.microservices.email_order_store_service.dao.inmemoryimplementation;

import com.codecool.microservices.email_order_store_service.dao.EmailDao;
import com.codecool.microservices.email_order_store_service.model.UserEmail;

import java.util.ArrayList;
import java.util.List;

public class EmailDaoMem implements EmailDao{
    private static EmailDaoMem ourInstance = new EmailDaoMem();

    public static EmailDaoMem getInstance() {
        return ourInstance;
    }

    private static List<UserEmail> DATA = new ArrayList<>();


    @Override
    public void add(UserEmail userEmail) {
        DATA.add(userEmail);
    }

    @Override
    public UserEmail find(String id) {
        return DATA.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void remove(String id) {
        DATA.remove(find(id));
    }

    @Override
    public void changeStatus(String id) {
        find(id).setStatus("done");
    }

    @Override
    public ArrayList getAll() {
        return (ArrayList) DATA;
    }
}
