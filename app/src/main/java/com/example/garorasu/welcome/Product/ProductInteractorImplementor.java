package com.example.garorasu.welcome.Product;

import android.os.AsyncTask;
import android.util.Log;

import com.example.garorasu.welcome.Videos.Video;
import com.example.garorasu.welcome.Videos.VideoPresenterImplementor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by garorasu on 25/11/16.
 */

public class ProductInteractorImplementor implements ProductInteractor {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static final String TAG = "Product";
    private ProductPresenterImplementor presenter;

    public ProductInteractorImplementor(ProductPresenterImplementor pre){
        this.presenter = pre;
    }

    @Override
    public void requestProducts() {
        new loadDataInBackground().execute();
    }
    private class loadDataInBackground extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("product");
            myRef.keepSynced(true);
            final ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Get Post object and use the values to update the UI
                    for(DataSnapshot ds:dataSnapshot.getChildren()) {
                        Product product = ds.getValue(Product.class);
                        presenter.sendDatatoAdapter(product);
                        System.out.println("Post data fetched from firebase is : " + product.getTitle());
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
