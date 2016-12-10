package com.codecool.shop.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class EmailServiceController {
    private static final String EMAIL_SENDER_URL = "http://localhost:60001/api/store";

    public static String sendEmail(String username, String subject, String email) throws IOException {
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(EMAIL_SENDER_URL);
            return execute(builder.addParameter("username", username).addParameter("subject", subject).addParameter("email", email).build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String execute(URI uri) throws IOException, URISyntaxException {
        return Request.Get(uri).execute().returnContent().asString();
    }
}
