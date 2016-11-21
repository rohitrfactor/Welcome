package com.example.garorasu.welcome.Videos;

/**
 * Created by garorasu on 21/11/16.
 */

public class VideoPresenterImplementor implements VideoPresenter {
    private final VideoInteractorImplementor interactor;
    private final VideoAdapterView adapter;
    private final VideoView view;


    public VideoPresenterImplementor(VideoAdapterView view,VideoView VideoView){
        this.adapter = view;
        this.interactor = new VideoInteractorImplementor(this);
        this.view = VideoView;
    }



    @Override
    public void sendDatatoAdapter(Video video) {
        adapter.addItem(video);
    }

    public void requestVideos(){
        if(view!=null){
            view.showProgress();
        }
        interactor.requestVideos();
    }
    public void onSuccess(){
        if(view!=null){
            view.hideProgress();
        }
    }
    public void onFailure(){
        if(view!=null){
            view.hideProgress();
        }
    }
    public void startVideo(Video video){
        if(view!=null){
            view.startVideo(video);
        }
    }
}

