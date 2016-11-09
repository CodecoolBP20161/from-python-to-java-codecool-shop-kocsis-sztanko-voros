package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import java.util.ArrayList;

public class ShoppingCartDaoMem implements ShoppingCartDao{

    private ArrayList<LineItem> DATA;

    public ShoppingCartDaoMem(){
        this.DATA = new ArrayList<>();
    }

    public LineItem find(Product product){
        return DATA.stream().filter(t -> t.getProduct().equals(product)).findFirst().orElse(null);
    }

    public LineItem find(int id){
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public void remove(int id){
        DATA.remove(id);
    }

    public void add(Product product){
        if(find(product) != null){
            LineItem toIncrement = find(product);
            toIncrement.setQuantity(toIncrement.getQuantity() + 1);
        } else {
            LineItem item = new LineItem(1, product);
            item.setId(DATA.size() + 1);
            DATA.add(item);
        }
    }

    public int getItemNumber(){
        if (DATA.size() == 0){ return 0;}
        return DATA.stream().map(t -> t.getQuantity())
                .reduce((t,z) -> t+z).get();
    }

    public float totalPrice(){
        return DATA.stream().map(t -> t.getProduct().getDefaultPrice()*t.getQuantity()).reduce((t,z) -> t+z).get();
    }

    public ArrayList getAll(){
        return DATA;
    }



}
