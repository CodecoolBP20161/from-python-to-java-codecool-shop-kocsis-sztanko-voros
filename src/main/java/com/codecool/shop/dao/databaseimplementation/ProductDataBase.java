package com.codecool.shop.dao.databaseimplementation;

public class ProductDataBase extends DataBaseAbstraction {
    @Override
    String createSQL() {
        return "CREATE TABLE product (id varchar(36) PRIMARY KEY)";
    }

    @Override
    protected String selectAllSQL() {
        return null;
    }
}
