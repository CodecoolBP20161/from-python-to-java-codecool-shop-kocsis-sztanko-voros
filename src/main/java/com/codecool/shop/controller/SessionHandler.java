package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.LineItemDaoMem;
import spark.Request;
import spark.Response;

// responsible for session management
public class SessionHandler {

    public static LineItemDaoMem getShoppingCartDaoMem(Request req, Response res) {
        if (req.session().attribute("cart") != null) {
            return req.session().attribute("cart");
        }
        LineItemDaoMem cart = new LineItemDaoMem();
        req.session().attribute("cart", cart);
        return cart;
    }
}
