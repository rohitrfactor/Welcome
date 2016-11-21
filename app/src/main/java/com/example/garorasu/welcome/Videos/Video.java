package com.example.garorasu.welcome.Videos;

import java.io.Serializable;

/**
 * Created by garorasu on 21/11/16.
 */

public class Video implements Serializable {
    private String header;
    private String vid;

    public Video(){}

    public String getHeader() {
        return header;
    }

    public String getVid() {
        return vid;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
}
