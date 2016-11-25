package com.example.garorasu.welcome.Product;

/**
 * Created by garorasu on 25/11/16.
 */

public class Product {
    private Long pid;
    private String title;
    private String price;
    private String url;

    public Product(){}
    public Product(Long id,String title,String price,String url){
        this.pid = id;
        this.title = title;
        this.price = price;
        this.url = url;
    }

    public Long getPid() {
        return pid;
    }
    public String getTitle() {
        return title;
    }
    public String getPrice() {
        return price;
    }
    public String getUrl() {
        return url;
    }
}
