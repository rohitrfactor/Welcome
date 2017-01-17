package com.pinnacle.garorasu.welcome.Main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pinnacle.garorasu.welcome.NavigationScreen;
import com.pinnacle.garorasu.welcome.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private ShimmerFrameLayout container;
    private static final long SPLASH_DISPLAY_LENGTH = 2000;
    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView title = (TextView) findViewById(R.id.text_splash);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/BungeeInline-Regular.ttf");
        title.setTypeface(custom_font);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        proceed();
    }
    public void proceed(){
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected){
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            // Build a GoogleApiClient with access to the Google Sign-In API and the
            // options specified by gso.
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
            //findViewById(R.id.sign_in_button).setOnClickListener(this);
            signIn();
        }
        else{
            new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                // Create an Intent that will start the Navigation-Activity.
                View view = findViewById(R.id.view_main);
                Intent x = new Intent(MainActivity.this,NavigationScreen.class);

                //MainActivity.this.startActivity(x, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) view.getContext(),view,"shimmer").toBundle());
                MainActivity.this.startActivity(x);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

        }
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
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            //updateUI(true);
            Toast.makeText(this,"Successfully logged in",Toast.LENGTH_SHORT).show();
            Intent x = new Intent(this,NavigationScreen.class);
            this.startActivity(x);
            this.finish();


        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this,"Unauthenticated",Toast.LENGTH_SHORT).show();
            //updateUI(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;

        }
    }
}
