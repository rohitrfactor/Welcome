package com.pinnacle.garorasu.welcome.Downloads;

import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pinnacle.garorasu.welcome.R;

import java.net.URI;

public class DownloadActivity extends AppCompatActivity implements DownloadView {
    private ProgressBar progressBar;
    private RecyclerView recycler;
    private DownloadRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        progressBar = (ProgressBar) findViewById(R.id.progress_download);
        recycler = (RecyclerView) findViewById(R.id.recycler_download);
        fillUI();
    }

    public boolean  isConnected(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
    public void fillUI(){
        if(!isConnected(getApplicationContext())){
            Toast.makeText(getApplicationContext(),"Check your internet connection",Toast.LENGTH_SHORT).show();
        }
        adapter = new DownloadRecyclerAdapter(this);
        adapter.request();
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler.setHasFixedSize(true);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }
    public void Download(final Download download){
        //StartNewDownload(Uri.parse("https://drive.google.com/open?id=0B6xrCQ0oGjVvc3RhcnRlcl9maWxl"));

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference httpsReference =
                storage.getReferenceFromUrl(
                        download.getUrl());
        //StorageReference islandRef = httpsReference.child("pdf-sample.pdf");

                    //File localFile = new File(Environment.getExternalStorageDirectory(), "sample.pdf");

        httpsReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                StartNewDownload(uri,download);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

    }
    public void StartNewDownload(Uri url,Download download)
    {
        DownloadManager.Request request = new DownloadManager.Request(url); /*init a request*/
        request.setDescription(download.getDescription()); //this description apears inthe android notification
        request.setTitle(download.getTitle());//this description apears inthe android notification
        request.setDestinationInExternalFilesDir(getApplicationContext(),
                "directory",
                "fileName"); //set destination
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //request.setDestinationInExternalFilesDir(getApplicationContext(), "PATH","SubPath");
        DownloadManager manager = (DownloadManager) getApplication().getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = manager.enqueue(request);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void startDownload(Download download) {
        Download(download);
        Toast.makeText(getApplicationContext(),download.getTitle()+" is being downloaded ... ",Toast.LENGTH_SHORT);
    }
}
