package com.pinnacle.garorasu.welcome.Downloads;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by garorasu on 19/1/17.
 */

public class DownloadInteractorImplementor implements DownloadInteractor {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static final String TAG = "Download";
    private DownloadPresenterImplementor presenter;

    public DownloadInteractorImplementor(DownloadPresenterImplementor pre){
        this.presenter = pre;
    }

    @Override
    public void requestMessages() {
        new DownloadInteractorImplementor.loadDataInBackground().execute();
    }

    private class loadDataInBackground extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("downloads");
            myRef.keepSynced(true);
            final ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Get Post object and use the values to update the UI
                    for(DataSnapshot ds:dataSnapshot.getChildren()) {
                        Download download = ds.getValue(Download.class);
                        presenter.sendDatatoAdapter(download);
                        System.out.println("Post data fetched from firebase is : " + download.getTitle());
                        presenter.onSuccess();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                    presenter.onFailure();
                    // ...
                }
            };
            myRef.addListenerForSingleValueEvent(postListener);
            return null;
        }
    }
}
