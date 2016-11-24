package com.codecool.shop.controller;


import com.codecool.shop.model.ShoppingCart;
import spark.Request;
import spark.Response;

// responsible for session management

public class SessionHandler {

    public static ShoppingCart getShoppingCartDaoMem(Request req, Response res) {
        if (req.session().attribute("cart") != null) {
            return req.session().attribute("cart");
        }
        ShoppingCart cart = new ShoppingCart();
        req.session().attribute("cart", cart);
        return cart;
    }
}
