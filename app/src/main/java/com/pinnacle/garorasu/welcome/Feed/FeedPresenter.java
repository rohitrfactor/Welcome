package com.pinnacle.garorasu.welcome.Feed;

/**
 * Created by garorasu on 14/11/16.
 */

public interface FeedPresenter {
    void sendDatatoAdapter(Feed f);
    void requestMessages();
    void startDetailScreen(Feed feed);
}
