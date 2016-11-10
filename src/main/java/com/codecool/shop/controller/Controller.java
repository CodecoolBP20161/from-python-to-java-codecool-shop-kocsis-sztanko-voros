package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Controller{

    public static ModelAndView renderProductsByFilter(Request req, Response res) {
        return PageController.renderProductsByFilter(req, res);
    }

    public static ModelAndView renderMain(Request req, Response res) {
        return PageController.renderMain(req, res);
    }

    public static String addCart(Request req, Response res) {
        return CartController.addCart(req, res);
    }

    public static ModelAndView renderCart(Request req, Response res) {
        return PageController.renderCart(req, res);
    }

    public static String decreaseItem(Request req, Response res) {
        return CartController.decreaseItem(req, res);
    }

    public static String increaseItem(Request req, Response res) {
       return CartController.increaseItem(req, res);
    }
}