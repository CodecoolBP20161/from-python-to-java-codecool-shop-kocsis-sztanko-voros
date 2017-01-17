package com.codecool.microservices.YMAL_service.controller;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by code on 2017.01.17..
 */

public class YMALcontroller {
    private String API_URL = "http://localhost:60002/api/preferences/";
    private String ACCESS_TOKEN = "1231234bndf";
    private final String ACCESS_TOKEN_PARAM_KEY = "accessToken";
    private final String USER_ID_PARAM_KEY = "userId";
    private final String CART_ITEM_ID_PARAM_KEY = "cartItemId";

    private static YMALcontroller instance;
    private ProductService productService;

    private YMALcontroller() {
        this.productService = ProductService.getInstance();
    }

    public static YMALcontroller getInstance() {
        if (instance == null) {
            instance = new YMALcontroller();
        }
        return instance;
    }


    public ArrayList<Product> getRecommendedProducts(int UserId) throws IOException, URISyntaxException {
        ArrayList<Integer> prodList = fetchProductList(UserId);
        ArrayList<Product> products = new ArrayList<>();
        for (int id : prodList) {
            Product p = productService.find(id);
            products.add(p);
        }
        return products;
    }

    public ArrayList<Integer> fetchProductList(int userId) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(API_URL + "select");
        builder.addParameter(ACCESS_TOKEN_PARAM_KEY, ACCESS_TOKEN);
        builder.addParameter(USER_ID_PARAM_KEY, Integer.toString(userId));
        JSONObject obj = new JSONObject(execute(builder.build()));
        JSONArray jsonArray = (JSONArray) obj.get("recommendations");
        ArrayList<Integer> productIdArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            productIdArray.add(Integer.parseInt(jsonArray.get(i).toString()));
        }
        return productIdArray;

    }

    public String save(int userId, int cartItemId) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(API_URL + "save");
        builder.addParameter(ACCESS_TOKEN_PARAM_KEY, ACCESS_TOKEN);
        builder.addParameter(USER_ID_PARAM_KEY, Integer.toString(userId));
        builder.addParameter(CART_ITEM_ID_PARAM_KEY, Integer.toString(cartItemId));
        System.out.println(builder.toString());
        return execute(builder.build());
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        YMALcontroller controller = YMALcontroller.getInstance();
        controller.save(1, 1);
        controller.save(2, 1);
        controller.save(2, 2);
        System.out.println(controller.getRecommendedProducts(1));
    }
}

