package com.codecool.microservices.email_order_store_service;

import com.codecool.microservices.email_order_store_service.controller.EmailStoreServiceController;
import com.codecool.microservices.email_order_store_service.service.EmailStoreService;

import java.net.URISyntaxException;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;

public class EmailStoreServer {

    private EmailStoreServiceController controller;

    public static void main(String[] args) {

        port(60001);

        EmailStoreServer application = new EmailStoreServer();

        application.controller = new EmailStoreServiceController(EmailStoreService.getInstance());

        // --- MAPPINGS ---
        get("/store", application.controller::storeEmail);
        get("/email", application.controller::getAllEmail);
        get("/changestatus", application.controller::changeStatus);

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
