package com.pinnacle.garorasu.welcome.Feed;

/**
 * Created by garorasu on 14/11/16.
 */

public class FeedPresenterImplementor implements FeedPresenter {
    private final FeedInteractorImplementor interactor;
    private final FeedAdapterView adapter;
    private final FeedView view;


    public FeedPresenterImplementor(FeedAdapterView view,FeedView feedView){
        this.adapter = view;
        this.interactor = new FeedInteractorImplementor(this);
        this.view = feedView;
    }

    @Override
    public void sendDatatoAdapter(Feed f) {
        adapter.addItem(f);
    }

    public void requestMessages(){
        if(view!=null){
            view.showProgress();
        }
        interactor.requestMessages();
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
    public void startDetailScreen(Feed feed){
        if(view!=null){
            view.startDetailScreen(feed);
        }
    }
}
