package com.codecool.microservices.email_order_store_service.model;

import java.util.UUID;

public class UserEmail {
    private String subject;
    private String email;
    private String username;
    private String id;
    private String status;

    public UserEmail(String subject, String email, String username) {
        this.subject = subject;
        this.email = email;
        this.username = username;
        this.id = UUID.randomUUID().toString();
        this.status = "new";
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
