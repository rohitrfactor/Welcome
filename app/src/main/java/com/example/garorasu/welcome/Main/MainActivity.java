package com.example.garorasu.welcome.Main;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;

import com.example.garorasu.welcome.Login.LoginActivity;
import com.example.garorasu.welcome.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends Activity {

    private ShimmerFrameLayout container;
    private static final long SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView title = (TextView) findViewById(R.id.text_splash);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/BungeeInline-Regular.ttf");
        title.setTypeface(custom_font);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                // Create an Intent that will start the Navigation-Activity.
                View view = findViewById(R.id.view_main);
                Intent x = new Intent(MainActivity.this,LoginActivity.class);

                MainActivity.this.startActivity(x, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) view.getContext(),view,"shimmer").toBundle());
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
