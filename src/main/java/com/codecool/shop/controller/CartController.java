package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.LineItemDaoMem;
import com.codecool.shop.model.LineItem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static com.codecool.shop.controller.SessionHandler.getShoppingCardDaoMem;

public class CartController {

    public static String addCart(Request req, Response res) {
        LineItemDaoMem cart = getShoppingCardDaoMem(req, res);
        ProductDao productDataStore = ProductDaoMem.getInstance();
        int id = Integer.valueOf(req.params("id"));
        cart.add(productDataStore.find(id));
        res.redirect("/");
        return null;
    }

    public static ModelAndView renderCart(Request req, Response res) {
        return PageController.renderCart(req, res);
    }

    public static String decreaseItem(Request req, Response res) {
        int id = Integer.valueOf(req.params("id"));
        System.out.println(id);
        LineItemDaoMem cart = getShoppingCardDaoMem(req, res);
        LineItem item = cart.find(id);
        item.setQuantity(item.getQuantity() - 1);
        if (item.getQuantity() == 0) {
            cart.remove(id);
        }
        res.redirect("/shoppingcart");
        return null;
    }

    public static String increaseItem(Request req, Response res) {
        int id = Integer.valueOf(req.params("id"));
        LineItemDaoMem cart = getShoppingCardDaoMem(req, res);
        LineItem item = cart.find(id);
        item.setQuantity(item.getQuantity() + 1);
        res.redirect("/shoppingcart");
        return null;
    }
}
