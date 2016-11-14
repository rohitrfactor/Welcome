package com.example.garorasu.welcome.Feed;

import android.util.Log;

import com.example.garorasu.welcome.Post;
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
    private DatabaseReference myRef,upref;
    private static final String TAG = "Feed";
    private FeedPresenterImplementor presenter;

    public FeedInteractorImplementor(FeedPresenterImplementor pre){
        this.presenter = pre;
    }

    @Override
    public void requestMessages() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("blog");
        //upref = myRef.child("blog");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    Feed feed = ds.getValue(Feed.class);
                    presenter.sendDatatoAdapter(feed);
                    System.out.println("Post data fetched from firebase is : " + feed.getHeader());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        myRef.addListenerForSingleValueEvent(postListener);
    }
}
