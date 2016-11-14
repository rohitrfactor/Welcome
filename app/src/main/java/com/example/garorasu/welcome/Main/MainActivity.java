package com.example.garorasu.welcome.Main;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.garorasu.welcome.Login.LoginActivity;
import com.example.garorasu.welcome.R;
import com.facebook.shimmer.ShimmerFrameLayout;


public class MainActivity extends AppCompatActivity {

    private ShimmerFrameLayout container;
    private static final long SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Navigation-Activity. */
                Intent x = new Intent(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivity(x);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void onResume(){
        super.onResume();
        //container = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        //container.startShimmerAnimation();
    }
    @Override
    public void onStart(){
        super.onStart();
        container = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmerAnimation();
    }
}
