package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import spark.Request;
import spark.Response;

public class SessionHandler {

    public static ShoppingCartDaoMem getShoppingCardDaoMem(Request req, Response res) {
        if (req.session().attribute("cart") != null) {
            return req.session().attribute("cart");
        }
        ShoppingCartDaoMem cart = new ShoppingCartDaoMem();
        req.session().attribute("cart", cart);
        return cart;
    }
}
