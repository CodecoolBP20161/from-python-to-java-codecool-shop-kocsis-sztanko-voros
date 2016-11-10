package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.LineItemDaoMem;
import spark.Request;
import spark.Response;

public class SessionHandler {

    public static LineItemDaoMem getShoppingCardDaoMem(Request req, Response res) {
        if (req.session().attribute("cart") != null) {
            return req.session().attribute("cart");
        }
        LineItemDaoMem cart = new LineItemDaoMem();
        req.session().attribute("cart", cart);
        return cart;
    }
}
