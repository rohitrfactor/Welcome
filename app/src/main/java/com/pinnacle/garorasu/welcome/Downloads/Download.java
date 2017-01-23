package com.pinnacle.garorasu.welcome.Downloads;

import java.io.Serializable;

/**
 * Created by garorasu on 19/1/17.
 */

public class Download implements Serializable {
    private String url;
    private long id;
    private String title;
    private String description;
    private String update;
    public Download(long id, String url, String title, String description,String update){
        this.url = url;
        this.id = id;
        this.title = title;
        this.description = description;
        this.update = update;
    }
    public Download(){}
    public String getUrl(){return url;}

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public String getUpdate() {return update;}
}
