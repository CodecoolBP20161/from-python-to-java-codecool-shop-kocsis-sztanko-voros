package com.codecool.shop.controller;

import com.codecool.microservices.YMAL_service.controller.YMALServiceController;
import com.codecool.shop.model.Filter;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.service.ProductCategoryService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.SupplierService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.codecool.shop.controller.SessionHandler.getShoppingCartDaoMem;

// controller responsible for page rendering

public class PageController {

    static private ProductCategoryService productCategoryDataStore = ProductCategoryService.getInstance();
    static private SupplierService productSupplierDataStore = SupplierService.getInstance();
    static private ProductService productDataStore = ProductService.getInstance();

    public static ModelAndView renderMain(Request req, Response res) {
        ShoppingCart LineItemDataStore = getShoppingCartDaoMem(req, res);
        Map params = new HashMap<>();
        params.put("cart", LineItemDataStore.getItemNumber());
        params.put("category", productCategoryDataStore.getAll());
        params.put("supplier", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }


    public static ModelAndView renderProductsByFilter(Request req, Response res) {
        ShoppingCart LineItemDataStore = getShoppingCartDaoMem(req, res);
        Map params = new HashMap<>();
        params.put("cart", LineItemDataStore.getItemNumber());
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

    public static ModelAndView renderCart(Request req, Response res) throws IOException, URISyntaxException {
        ShoppingCart LineItemDataStore = getShoppingCartDaoMem(req, res);
        Map params = new HashMap<>();
        params.put("products", YMALServiceController.getInstance().getRecommendedProducts(req.session().id()));
        params.put("lineItems", LineItemDataStore.getAll());
        params.put("total", Math.round(LineItemDataStore.totalPrice() * 10.0) / 10.0);
        return new ModelAndView(params, "product/shoppingcart");
    }
}
