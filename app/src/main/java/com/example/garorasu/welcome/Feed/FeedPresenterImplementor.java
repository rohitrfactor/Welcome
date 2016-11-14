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
        System.out.println("send data to adapter");
        adapter.addItem(f);
    }

    public void requestMessages(){
        System.out.println("In presenter : request messages");
        interactor.requestMessages();
    }
}
