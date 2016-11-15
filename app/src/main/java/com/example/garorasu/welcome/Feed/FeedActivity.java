package com.example.garorasu.welcome.Feed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.garorasu.welcome.NavigationScreen;
import com.example.garorasu.welcome.Quiz.QuizActivity;
import com.example.garorasu.welcome.R;
import com.example.garorasu.welcome.Study.StudyActivity;
import com.example.garorasu.welcome.Videos.VideosActivity;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity implements FeedView, View.OnClickListener{
    private ProgressBar progressBar;
    private RecyclerView recycler;
    private FeedRecyclerAdapter adapter;

    private static final String TAG = "FeedActivity";
    private ResideMenu resideMenu;
    private FeedActivity mContext;
    private ResideMenuItem itemFeed;
    private ResideMenuItem itemStudy;
    private ResideMenuItem itemQuiz;
    private ResideMenuItem itemVideos;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        mContext = this;
        setUpMenu();
        fillUI();
    }
    public void fillUI(){
        adapter = new FeedRecyclerAdapter(this);
        adapter.request();
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler.setHasFixedSize(true);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
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
    public void request() {

    }

    @Override
    public void addItem(Feed f) {

    }

    @Override
    public void onClick(View view) {

        if (view == itemFeed){

        }else if (view == itemStudy){
            Intent study = new Intent(this,StudyActivity.class);
            this.startActivity(study);
            this.finish();
        }else if (view == itemQuiz){
            Intent quiz = new Intent(this,QuizActivity.class);
            this.startActivity(quiz);
            this.finish();
        }else if (view == itemVideos){
            Intent videos = new Intent(this,VideosActivity.class);
            this.startActivity(videos);
            this.finish();
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
