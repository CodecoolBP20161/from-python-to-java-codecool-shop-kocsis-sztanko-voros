package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProductsByFilter(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.getAll());
        params.put("supplier", productSupplierDataStore.getAll());
        int id = Integer.valueOf(req.params("id"));
        if (req.uri().contains("category")) {
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(id)));
            params.put("current", productCategoryDataStore.find(id));
        }
        else {
            params.put("products", productDataStore.getBy(productSupplierDataStore.find(id)));
            params.put("current", productSupplierDataStore.find(id));
        }
        return new ModelAndView(params, "product/index");
    }


    public static ModelAndView renderMain(Request req, Response res) {
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = getShoppingCardDaoMem(req, res);
        Map params = new HashMap<>();
        params.put("cart", shoppingCartDataStore.getItemNumber());
        params.put("category", productCategoryDataStore.getAll());
        params.put("supplier", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ShoppingCartDaoMem getShoppingCardDaoMem(Request req, Response res) {
        if (req.session().attribute("cart") != null) {
            return req.session().attribute("cart");
        }
        ShoppingCartDaoMem cart = new ShoppingCartDaoMem();
        req.session().attribute("cart", cart);
        return cart;
    }

    public static String addCart(Request req, Response res) {
        ShoppingCartDaoMem cart = getShoppingCardDaoMem(req, res);
        ProductDao productDataStore = ProductDaoMem.getInstance();
        int id = Integer.valueOf(req.params("id"));
        cart.add(productDataStore.find(id));
        req.session().attribute("cart", cart);
        res.redirect("/");
        return null;

    }

    public static ModelAndView renderCart(Request req, Response res){
        ShoppingCartDao shoppingCartDataStore = getShoppingCardDaoMem(req, res);
        Map params = new HashMap<>();
        params.put("lineItems", shoppingCartDataStore.getAll());
        return new ModelAndView(params, "product/shoppingcart");
    }


}
