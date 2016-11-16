package com.example.garorasu.welcome.FeedDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.garorasu.welcome.Feed.Feed;
import com.example.garorasu.welcome.R;

public class FeedDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        toolbar.setTitle("Article");
        setSupportActionBar(toolbar);
        Intent feedIntent = getIntent();
        Feed feed = (Feed) feedIntent.getSerializableExtra("FEED");
        toolbar.setTitle(feed.getHeader());
        WebView content = (WebView) findViewById(R.id.content_text);
        String text = "<html><body>"
                +"<p align=\"justify\">"
                +feed.getPreviewContent()
                + "</p> "
                + "</body></html>";
        content.loadData(text,"text/html","utf-8");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
