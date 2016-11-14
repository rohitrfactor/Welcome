package com.example.garorasu.welcome.Feed;

/**
 * Created by garorasu on 14/11/16.
 */

public class FeedPresenterImplementor implements FeedPresenter {
    private final FeedInteractorImplementor interactor;
    private final FeedAdapterView adapter;
    public FeedPresenterImplementor(FeedAdapterView view){
        this.adapter = view;
        this.interactor = new FeedInteractorImplementor(this);
    }
    @Override
    public void sendDatatoAdapter(Feed f) {
        adapter.addItem(f);
    }

    public void requestMessages(){
        interactor.requestMessages();
    }
}
