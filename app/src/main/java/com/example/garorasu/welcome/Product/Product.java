package com.example.garorasu.welcome.Product;

import java.io.Serializable;

/**
 * Created by garorasu on 25/11/16.
 */

public class Product implements Serializable {
    private Long pid;
    private String title;
    private String description;
    private String price;
    private String url;

    public Product(){}
    public Product(Long id,String title,String price,String description,String url){
        this.pid = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.url = url;
    }
    public String getDescription() {return description;}
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
