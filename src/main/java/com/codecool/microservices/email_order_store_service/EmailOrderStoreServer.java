package com.codecool.microservices.email_order_store_service;

import com.codecool.microservices.email_order_store_service.controller.EmailOrderStoreServiceController;
import com.codecool.microservices.email_order_store_service.service.EmailOrderStoreService;

import java.net.URISyntaxException;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;

public class EmailOrderStoreServer {

    private EmailOrderStoreServiceController controller;

    public static void main(String[] args) {

        port(60001);

        EmailOrderStoreServer application = new EmailOrderStoreServer();

        application.controller = new EmailOrderStoreServiceController(EmailOrderStoreService.getInstance());

        // --- MAPPINGS ---
        get("/api/store", application.controller::storeEmail);
        get("/api/email", application.controller::getAllEmail);
        get("/api/changestatus", application.controller::changeStatus);

        // --- EXCEPTION HANDLING ---
        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
        });
    }
}
