package com.codecool.microservices.email_order_store_service.dao.inmemoryimplementation;

import com.codecool.microservices.email_order_store_service.dao.EmailOrderDao;
import com.codecool.microservices.email_order_store_service.model.EmailOrder;

import java.util.ArrayList;
import java.util.List;

public class EmailOrderDaoMem implements EmailOrderDao {
    private static EmailOrderDaoMem ourInstance = new EmailOrderDaoMem();

    public static EmailOrderDaoMem getInstance() {
        return ourInstance;
    }

    private static List<EmailOrder> DATA = new ArrayList<>();


    @Override
    public void add(EmailOrder emailOrder) {
        DATA.add(emailOrder);
    }

    @Override
    public EmailOrder find(String id) {
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
    public ArrayList getAllNew() {
        ArrayList newEmails = new ArrayList();
        for (EmailOrder emailOrder : DATA) {
            if (emailOrder.getStatus().equals("new")){
                newEmails.add(emailOrder);
            }
        }
        return newEmails;
    }
}
