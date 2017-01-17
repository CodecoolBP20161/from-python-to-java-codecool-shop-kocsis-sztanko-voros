package com.codecool.shop.model;

public class UserModel {
    private String name;
    private String email;
    private String passwordHash;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    private String passwordSalt;

    private UserModel(UserBuilder userBuilder) {
        this.name = userBuilder.name;
        this.email = userBuilder.email;
        this.passwordHash = userBuilder.passwordHash;
        this.passwordSalt = userBuilder.passwordSalt;
    }

    public static class UserBuilder {
        private String name;
        private String email;
        private String passwordHash;
        private String passwordSalt;

        public UserBuilder(String name, String email, String passwordHash, String passwordSalt) {
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
