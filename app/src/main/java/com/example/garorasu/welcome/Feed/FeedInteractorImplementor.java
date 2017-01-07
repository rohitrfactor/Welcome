package com.example.garorasu.welcome.Feed;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by garorasu on 14/11/16.
 */

public class FeedInteractorImplementor implements FeedInteractor {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static final String TAG = "Feed";
    private FeedPresenterImplementor presenter;

    public FeedInteractorImplementor(FeedPresenterImplementor pre){
        this.presenter = pre;
    }

    @Override
    public void requestMessages() {
        new loadDataInBackground().execute();
    }

    private class loadDataInBackground extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("blog");
            myRef.keepSynced(true);
            final ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Get Post object and use the values to update the UI
                    for(DataSnapshot ds:dataSnapshot.getChildren()) {
                        Feed feed = ds.getValue(Feed.class);
                        presenter.sendDatatoAdapter(feed);
                        System.out.println("Post data fetched from firebase is : " + feed.getHeader());
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
