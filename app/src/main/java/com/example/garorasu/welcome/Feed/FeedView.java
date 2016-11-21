package com.example.garorasu.welcome.Feed;

import java.util.List;

/**
 * Created by garorasu on 14/11/16.
 */

public interface FeedView {
    void showProgress();
    void hideProgress();
    void startDetailScreen(Feed feed);
}
