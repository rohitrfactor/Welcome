package com.pinnacle.garorasu.welcome.Downloads;



/**
 * Created by garorasu on 19/1/17.
 */

public interface DownloadView {
    void showProgress();
    void hideProgress();
    void startDownload(Download download);
}
