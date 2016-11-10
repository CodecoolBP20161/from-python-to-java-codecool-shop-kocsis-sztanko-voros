package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Filter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static com.codecool.shop.controller.SessionHandler.getShoppingCardDaoMem;

public class PageController {

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


    public static ModelAndView renderProductsByFilter(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ShoppingCartDao shoppingCartDataStore = getShoppingCardDaoMem(req, res);
        Map params = new HashMap<>();
        params.put("cart", shoppingCartDataStore.getItemNumber());
        params.put("category", productCategoryDataStore.getAll());
        params.put("supplier", productSupplierDataStore.getAll());
        int id = Integer.valueOf(req.params("id"));
        Filter filter = productSupplierDataStore.find(id);
        if (req.params("type").equals("category")) {
            filter = productCategoryDataStore.find(id);
        }

        params.put("products", filter.getBy());
        params.put("current", filter);
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderCart(Request req, Response res) {
        ShoppingCartDao shoppingCartDataStore = getShoppingCardDaoMem(req, res);
        Map params = new HashMap<>();
        params.put("lineItems", shoppingCartDataStore.getAll());
        params.put("total", shoppingCartDataStore.totalPrice());
        return new ModelAndView(params, "product/shoppingcart");
    }
}
