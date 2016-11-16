package com.example.garorasu.welcome.FeedDetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.garorasu.welcome.Feed.Feed;
import com.example.garorasu.welcome.R;

import org.w3c.dom.Text;

import java.net.URL;

public class FeedDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private WebView content;
    private ProgressDialog dialog;
    final FeedDetailActivity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Article");
        Intent feedIntent = getIntent();
        Feed feed = (Feed) feedIntent.getSerializableExtra("FEED");
        content = (WebView) findViewById(R.id.content_text);
        TextView header = (TextView)findViewById(R.id.title_header);
        dialog = new ProgressDialog(FeedDetailActivity.this);
        header.setText(feed.getHeader());
//        getWindow().requestFeature(Window.FEATURE_PROGRESS);

        content.getSettings().setJavaScriptEnabled(true);


        content.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                activity.setProgress(progress * 1000);
            }
        });
        content.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setMessage("Loading..Please wait.");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        content.loadUrl(feed.getUrl());
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
