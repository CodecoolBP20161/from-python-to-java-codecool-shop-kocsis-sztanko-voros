package com.codecool.shop.model;

import java.util.UUID;

public class UserModel {
    private String id;
    private String name;
    private String email;
    private String passwordHash;
    private String passwordSalt;

    private UserModel (UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.name = userBuilder.name;
        this.email = userBuilder.email;
        this.passwordHash = userBuilder.passwordHash;
        this.passwordSalt = userBuilder.passwordSalt;
    }

    public static class UserBuilder {
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

        public UserModel build() {
            return new UserModel(this);
        }
    }
}
