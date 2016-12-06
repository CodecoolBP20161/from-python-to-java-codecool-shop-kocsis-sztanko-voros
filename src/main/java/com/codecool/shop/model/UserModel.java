package com.codecool.shop.model;

import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String email;
    private String passwordHash;
    private String passwordSalt;

    private User (UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.name = userBuilder.name;
        this.email = userBuilder.email;
        this.passwordHash = userBuilder.passwordHash;
        this.passwordSalt = userBuilder.passwordSalt;
    }

    private static class UserBuilder {
        private String id;
        private String name;
        private String email;
        private String passwordHash;
        private String passwordSalt;

        public UserBuilder(String name, String email, String passwordHash, String passwordSalt) {
            this.id = UUID.randomUUID().toString();
            this.name = name;
            this.email = email;
            this.passwordHash = passwordHash;
            this.passwordSalt = passwordSalt;
        }

        public User build() {
            return new User(this);
        }
    }
}
