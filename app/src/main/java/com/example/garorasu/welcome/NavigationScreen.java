
package com.example.garorasu.welcome;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.example.garorasu.welcome.Feed.FeedFragment;
import com.example.garorasu.welcome.Quiz.QuizFragment;
import com.example.garorasu.welcome.Study.StudyFragment;
import com.example.garorasu.welcome.Videos.VideosFragment;
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
    private ResideMenuItem itemSettings;
    private FragmentTransaction transaction;
    private FeedFragment feedFragment;
    private QuizFragment quizFragment;
    private StudyFragment studyFragment;
    private VideosFragment videosFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Articles");
        myToolbar.setNavigationIcon(R.drawable.icon_selector);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
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
            feedFragment = new FeedFragment();
            quizFragment = new QuizFragment();
            studyFragment = new StudyFragment();
            videosFragment = new VideosFragment();
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            //firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container,feedFragment).commit();
        }
        setUpMenu();
    }
    @Override
    public void onClick(View view) {

        if (view == itemFeed){
            transaction.replace(R.id.fragment_container,feedFragment).commit();
        }else if (view == itemStudy){
            transaction.replace(R.id.fragment_container,studyFragment).commit();
        }else if (view == itemQuiz){
            transaction.replace(R.id.fragment_container,quizFragment).commit();
        }else if (view == itemVideos){
            transaction.replace(R.id.fragment_container,videosFragment).commit();
        }else if(view == itemSettings){
            //Intent settings = new Intent(this,Settings.class);
            //this.startActivity(settings);
        }
        resideMenu.closeMenu();

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }
    public void setUpMenu(){

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        // create menu items;
        String titles[] = { "Feed", "Study", "Quiz", "Videos","Settings" };
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

        itemSettings = new ResideMenuItem(this, icon[3], titles[4]);
        itemSettings.setOnClickListener(this);
        resideMenu.addMenuItem(itemSettings,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT

    }
}

