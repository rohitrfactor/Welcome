
package com.pinnacle.garorasu.welcome;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.pinnacle.garorasu.welcome.Downloads.DownloadActivity;
import com.pinnacle.garorasu.welcome.Feed.FeedFragment;
import com.pinnacle.garorasu.welcome.Product.ProductFragment;
import com.pinnacle.garorasu.welcome.Quiz.Avatar;
import com.pinnacle.garorasu.welcome.Quiz.Category.CategorySelectionActivity;
import com.pinnacle.garorasu.welcome.Quiz.Player;
import com.pinnacle.garorasu.welcome.Quiz.PreferencesHelper;
import com.pinnacle.garorasu.welcome.R;
import com.pinnacle.garorasu.welcome.Study.StudyFragment;
import com.pinnacle.garorasu.welcome.Videos.VideoFragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class NavigationScreen extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "NavigationScreen";
    private ResideMenu resideMenu;
    private NavigationScreen mContext;
    private ResideMenuItem itemFeed;
    private ResideMenuItem itemStudy;
    private ResideMenuItem itemQuiz;
    private ResideMenuItem itemVideos;
    private ResideMenuItem itemProducts;
    private ResideMenuItem itemSettings;
    private ActionBar action;
    private Player mPlayer;

    private static final long FRAGMENT_DISPLAY_LENGTH = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_screen);
      /*  Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Articles");
        myToolbar.setNavigationIcon(R.drawable.icon_selector);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        */
        action = getSupportActionBar();
        action.hide();
        mContext = this;
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            // Create a new Fragment to be placed in the activity layout
            FeedFragment feedFragment = new FeedFragment();
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,feedFragment).commit();
            action.setTitle("Articles");

        }
        //setUpMenu();
    }
    @Override
    public void onClick(View view) {
        if (view == itemFeed){
              action.setTitle("Articles");
              resideMenu.clearIgnoredViewList();
              FeedFragment feedFragment = new FeedFragment();
              getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,feedFragment).commit();
        }else if (view == itemStudy){
              action.setTitle("Study");
              resideMenu.clearIgnoredViewList();
              StudyFragment studyFragment = new StudyFragment();
              getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,studyFragment).commit();
        }else if (view == itemQuiz){
              resideMenu.clearIgnoredViewList();
              performSignInWithTransition();
              //this.finish();
              //action.setTitle("Quizes");
              //QuizFragment quizFragment = new QuizFragment();
              //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,quizFragment).commit();
        }else if (view == itemVideos){
              FrameLayout ignored_view = (FrameLayout) findViewById(R.id.fragment_container);
              resideMenu.addIgnoredView(ignored_view);
              action.setTitle("Video");
              VideoFragment videoFragment = new VideoFragment();
              getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, videoFragment).commit();

        }else if (view == itemProducts){
              action.setTitle("Products");
              ProductFragment productFragment = new ProductFragment();
              getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, productFragment).commit();
        }
        else if(view == itemSettings){
             //action.setTitle("Settings");
            //Intent settings = new Intent(this,Settings.class);
            //this.startActivity(settings);
        }     new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                resideMenu.closeMenu();
            }
        }, FRAGMENT_DISPLAY_LENGTH);
    }
    private void performSignInWithTransition() {
            mPlayer = new Player("Name", "L",
                    Avatar.values()[1]);
            PreferencesHelper.writeToPreferences(this, mPlayer);
        CategorySelectionActivity.start(this, mPlayer);
    }
    @Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first
        //setUpMenu();
        setBottomNavigation();
    }

    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first
    }
    //@Override
    //public boolean dispatchTouchEvent(MotionEvent ev) {return resideMenu.dispatchTouchEvent(ev);}

    public void setUpMenu(){
        action.hide();
        //if(resideMenu != null){resideMenu.removeAllViews();};
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.setScaleValue(0.6f);
        resideMenu.scheduleLayoutAnimation();
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        resideMenu.attachToActivity(this);

        // create menu items;
        String titles[] = { "Feed", "Study", "Quiz", "Video","Products","Settings" };
        int icon[] = { R.drawable.icon_home, R.drawable.icon_profile, R.drawable.icon_calendar, R.drawable.icon_settings };
            itemFeed = new ResideMenuItem(this, icon[0], titles[0]);
            itemFeed.setOnClickListener(this);
            resideMenu.addMenuItem(itemFeed,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT

            itemStudy = new ResideMenuItem(this, icon[1], titles[1]);
            itemStudy.setOnClickListener(this);
            resideMenu.addMenuItem(itemStudy,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT

            itemQuiz = new ResideMenuItem(this, icon[2], titles[2]);
            itemQuiz.setOnClickListener(this);
            resideMenu.addMenuItem(itemQuiz,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT

            itemVideos = new ResideMenuItem(this, icon[3], titles[3]);
            itemVideos.setOnClickListener(this);
            resideMenu.addMenuItem(itemVideos,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT

            itemProducts = new ResideMenuItem(this, icon[3], titles[4]);
            itemProducts.setOnClickListener(this);
            resideMenu.addMenuItem(itemProducts,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT

            itemSettings = new ResideMenuItem(this, icon[3], titles[5]);
            itemSettings.setOnClickListener(this);
            resideMenu.addMenuItem(itemSettings,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT
            //resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }
    public void fabButton(View view){
        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }

    public void setBottomNavigation(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                action.setTitle("Articles");
                                FeedFragment feedFragment = new FeedFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,feedFragment).commit();
                                break;
                            case R.id.action_schedules:
                                performSignInWithTransition();
                                break;
                            case R.id.action_music:
                                action.setTitle("Video");
                                VideoFragment videoFragment = new VideoFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, videoFragment).commit();
                                break;
                            case R.id.action_products:
                                action.setTitle("Products");
                                ProductFragment productFragment = new ProductFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, productFragment).commit();
                                break;
                        }
                        return false;
                    }
                });
    }
    public void visitDownloadSection(View view){
        Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
        startActivity(intent);
    }

}

