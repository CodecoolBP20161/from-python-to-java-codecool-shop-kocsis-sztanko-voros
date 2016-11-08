package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao{
    private static List<LineItem> DATA = new ArrayList<>();
}
