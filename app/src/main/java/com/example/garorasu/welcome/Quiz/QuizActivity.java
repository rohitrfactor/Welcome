package com.example.garorasu.welcome.Quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.example.garorasu.welcome.NavigationScreen;
import com.example.garorasu.welcome.R;
import com.example.garorasu.welcome.Study.StudyActivity;
import com.example.garorasu.welcome.Videos.VideosActivity;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private ResideMenu resideMenu;
    private QuizActivity mContext;
    private ResideMenuItem itemFeed;
    private ResideMenuItem itemStudy;
    private ResideMenuItem itemQuiz;
    private ResideMenuItem itemVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Quizes");
        myToolbar.setNavigationIcon(R.drawable.icon_selector);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        mContext = this;
        setUpMenu();

    }
    @Override
    public void onClick(View view) {
        if (view == itemFeed){
            Intent feed = new Intent(this,NavigationScreen.class);
            this.startActivity(feed);
            this.finish();
        }else if (view == itemStudy){
            Intent study = new Intent(this,StudyActivity.class);
            this.startActivity(study);
            this.finish();
        }else if (view == itemQuiz){

        }else if (view == itemVideos){
            Intent videos = new Intent(this,VideosActivity.class);
            this.startActivity(videos);
            this.finish();
        }
        System.out.println("Exit");
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
        String titles[] = { "Feed", "Study", "Quiz", "Videos" };
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

    }
}
