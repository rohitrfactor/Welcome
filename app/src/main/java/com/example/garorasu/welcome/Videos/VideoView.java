package com.example.garorasu.welcome.Videos;

/**
 * Created by garorasu on 21/11/16.
 */

public interface VideoView {
    void showProgress(String link);
    void hideProgress(String link);
    void startVideo(Video video);
}
