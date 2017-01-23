package com.pinnacle.garorasu.welcome.Downloads;



/**
 * Created by garorasu on 19/1/17.
 */

public class DownloadPresenterImplementor implements DownloadPresenter {
    private final DownloadInteractorImplementor interactor;
    private final DownloadAdapterView adapter;
    private final DownloadView view;


    public DownloadPresenterImplementor(DownloadAdapterView view, DownloadView downloadView){
        this.adapter = view;
        this.interactor = new DownloadInteractorImplementor(this);
        this.view = downloadView;
    }

    @Override
    public void sendDatatoAdapter(Download d) {
        adapter.addItem(d);
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
    public void startDownload(Download download){
        if(view!=null){
            view.startDownload(download);
        }
    }
}
