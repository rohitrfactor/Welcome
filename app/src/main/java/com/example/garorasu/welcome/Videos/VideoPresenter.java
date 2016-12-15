package com.example.garorasu.welcome.Videos;

/**
 * Created by garorasu on 21/11/16.
 */

public interface VideoPresenter {
    void sendDatatoAdapter(Video video);
    void requestVideos(String link);
    void startVideo(Video video);
}
