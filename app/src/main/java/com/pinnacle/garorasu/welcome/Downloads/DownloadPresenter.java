package com.pinnacle.garorasu.welcome.Downloads;


/**
 * Created by garorasu on 19/1/17.
 */

public interface DownloadPresenter {
    void sendDatatoAdapter(Download d);
    void requestMessages();
    void startDownload(Download download);
}
