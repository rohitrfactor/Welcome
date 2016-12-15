package com.example.garorasu.welcome.Videos;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by garorasu on 21/11/16.
 */

public class VideoInteractorImplementor implements VideoInteractor {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static final String TAG = "Video";
    private VideoPresenterImplementor presenter;

    public VideoInteractorImplementor(VideoPresenterImplementor pre){
        this.presenter = pre;
    }

    @Override
    public void requestVideos(String link) {
        new loadDataInBackGround().execute(link);
    }
    private class loadDataInBackGround extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("video/"+params[0]);
            myRef.keepSynced(true);
            final ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Get Post object and use the values to update the UI
                    for(DataSnapshot ds:dataSnapshot.getChildren()) {
                        Video Video = ds.getValue(Video.class);
                        presenter.sendDatatoAdapter(Video);
                        System.out.println("Post data fetched from firebase is : " + Video.getHeader());
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

