package com.example.garorasu.welcome.Feed;

/**
 * Created by garorasu on 14/11/16.
 */

public class Feed {
    private Long blogid;
    private String duration;
    private String header;
    private String previewContent;

    public Feed() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Feed(Long blogid, String duration, String header, String previewContent) {
        this.blogid = blogid;
        this.duration = duration;
        this.header =  header;
        this.previewContent = previewContent;
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
}
