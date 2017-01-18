package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.inmemoryimplementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.ShoppingCart;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.codecool.shop.controller.SessionHandler.getShoppingCartDaoMem;

//Controller responsible for cart operations

public class CartController {

    public static String addCart(Request req, Response res) throws IOException, URISyntaxException {
        ShoppingCart cart = getShoppingCartDaoMem(req, res);
        ProductDao productDataStore = ProductDaoMem.getInstance();
        int id = Integer.valueOf(req.params("id"));
        YMALServiceController.getInstance().save(req.session().id(), id);
        cart.add(productDataStore.find(id));
        res.redirect("/");
        return null;
    }

    public static String decreaseItem(Request req, Response res) {
        int id = Integer.valueOf(req.params("id"));
        ShoppingCart cart = getShoppingCartDaoMem(req, res);
        LineItem item = cart.find(id);
        item.setQuantity(item.getQuantity() - 1);
        item.refreshSubTotal();
        if (item.getQuantity() == 0) {
            cart.remove(id);
        }
        res.redirect("/shoppingcart");
        return null;
    }

    public static String increaseItem(Request req, Response res) throws IOException, URISyntaxException {
        int id = Integer.valueOf(req.params("id"));
        ShoppingCart cart = getShoppingCartDaoMem(req, res);
        LineItem item = cart.find(id);
        item.setQuantity(item.getQuantity() + 1);
        item.refreshSubTotal();
        res.redirect("/shoppingcart");
        return null;
    }
}
