package com.codecool.microservices.YMAL_service.controller;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpHostConnectException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by code on 2017.01.17..
 */

public class YMALServiceController {
    private String API_URL = "http://localhost:60002/api/preferences/";
    private String ACCESS_TOKEN = "1231234bndf";
    private final String ACCESS_TOKEN_PARAM_KEY = "accessToken";
    private final String USER_ID_PARAM_KEY = "userId";
    private final String CART_ITEM_ID_PARAM_KEY = "cartItemId";

    private static YMALServiceController instance;
    private ProductService productService;

    private YMALServiceController() {
        this.productService = ProductService.getInstance();
    }

    public static YMALServiceController getInstance() {
        if (instance == null) {
            instance = new YMALServiceController();
        }
        return instance;
    }

    public ArrayList<Product> getRecommendedProducts(String UserId) throws IOException, URISyntaxException {
        ArrayList<Integer> prodList = fetchProductList(UserId);
        ArrayList<Product> products = new ArrayList<>();
        for (int id : prodList) {
            Product p = productService.find(id);
            products.add(p);
        }
        return products;
    }

    public ArrayList<Integer> fetchProductList(String userId) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(API_URL + "select");
        builder.addParameter(ACCESS_TOKEN_PARAM_KEY, ACCESS_TOKEN);
        builder.addParameter(USER_ID_PARAM_KEY, userId);
        ArrayList<Integer> productIdArray = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(execute(builder.build()));
            JSONArray jsonArray = (JSONArray) obj.get("recommendations");
            for (int i = 0; i < jsonArray.length(); i++) {
                productIdArray.add(Integer.parseInt(jsonArray.get(i).toString()));
            }
        } catch (HttpHostConnectException e) {
            // microservice is not running, return value: empty ArrayList
            return productIdArray;
        }
        return productIdArray;
    }

    public String save(String userId, int cartItemId) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(API_URL + "save");
        builder.addParameter(ACCESS_TOKEN_PARAM_KEY, ACCESS_TOKEN);
        builder.addParameter(USER_ID_PARAM_KEY, userId);
        builder.addParameter(CART_ITEM_ID_PARAM_KEY, Integer.toString(cartItemId));
        return execute(builder.build());
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}

