package com.example.garorasu.welcome.Product;

/**
 * Created by garorasu on 25/11/16.
 */

public class Product {
    private String pid;
    private String title;
    private int price;
    private String url;

    public Product(){}
    public Product(String id,String title,int price,String url){
        this.pid = id;
        this.title = title;
        this.price = price;
        this.url = url;
    }

    public String getPid() {
        return pid;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }
}
