package com.example.garorasu.welcome.Feed;

import java.io.Serializable;

/**
 * Created by garorasu on 14/11/16.
 */

public class Feed implements Serializable {
    private Long blogid;
    private String duration;
    private String header;
    private String previewContent;
    private String url;

    public Feed() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Feed(Long blogid, String duration, String header, String previewContent,String url) {
        this.blogid = blogid;
        this.duration = duration;
        this.header =  header;
        this.previewContent = previewContent;
        this.url=url;
    }
    //Getters
    public String getHeader(){return header;}
    public Long getBlogid(){
        return blogid;
    }
    public String getDuration(){
        return duration;
    }
    public String getPreviewContent(){
        return previewContent;
    }
    public String getUrl(){return url;}
}
