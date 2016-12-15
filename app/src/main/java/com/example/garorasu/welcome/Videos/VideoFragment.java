package com.example.garorasu.welcome.Videos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.garorasu.welcome.Feed.FeedRecyclerAdapter;
import com.example.garorasu.welcome.Login.LoginActivity;
import com.example.garorasu.welcome.Main.MainActivity;
import com.example.garorasu.welcome.R;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.squareup.picasso.Picasso;

public class VideoFragment extends Fragment implements VideoView,View.OnClickListener  {
    private ProgressBar progressBarPopular,progressBarMath,progressBarEnglish,progressBarReasoning,progressBarGk;
    private RecyclerView recyclerPopular,recyclerMath,recyclerEnglish,recyclerReasoning,recyclerGk;
    private VideoRecyclerAdapter adapterPopular,adapterMath,adapterEnglish,adapterReasoning,adapterGk;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_video, container, false);

        //TextView title = (TextView) view.findViewById(R.id.video_fragment_header);
      //  TextView subtitle = (TextView) view.findViewById(R.id.video_fragment_sub_header);
      //  Typeface custom_font = Typeface.createFromAsset(getResources().getAssets(),  "fonts/SpecialElite.ttf");
      //  title.setTypeface(custom_font);
      //  subtitle.setTypeface(custom_font);

        progressBarPopular = (ProgressBar) view.findViewById(R.id.progress_video_popular);
        recyclerPopular = (RecyclerView) view.findViewById(R.id.recycler_video_popular);

        progressBarMath = (ProgressBar) view.findViewById(R.id.progress_video_math);
        recyclerMath = (RecyclerView) view.findViewById(R.id.recycler_video_math);

        progressBarEnglish = (ProgressBar) view.findViewById(R.id.progress_video_english);
        recyclerEnglish = (RecyclerView) view.findViewById(R.id.recycler_video_english);

        progressBarReasoning = (ProgressBar) view.findViewById(R.id.progress_video_reasoning);
        recyclerReasoning = (RecyclerView) view.findViewById(R.id.recycler_video_reasoning);

        progressBarGk = (ProgressBar) view.findViewById(R.id.progress_video_gk);
        recyclerGk = (RecyclerView) view.findViewById(R.id.recycler_video_gk);

        fillUI();
        //ImageView videoHeaderImage = (ImageView) view.findViewById(R.id.video_header_image);
        //Picasso.with(getContext()).load(R.drawable.tape).into(videoHeaderImage);
        return view;
    }
    public void fillUI(){
        adapterPopular = new VideoRecyclerAdapter(this,"popular");
        adapterPopular.request();
        recyclerPopular.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerPopular.setHasFixedSize(true);
        recyclerPopular.setItemAnimator(new DefaultItemAnimator());
        recyclerPopular.setAdapter(adapterPopular);

        adapterMath = new VideoRecyclerAdapter(this,"math");
        adapterMath.request();
        recyclerMath.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerMath.setHasFixedSize(true);
        recyclerMath.setItemAnimator(new DefaultItemAnimator());
        recyclerMath.setAdapter(adapterMath);

        adapterEnglish = new VideoRecyclerAdapter(this,"english");
        adapterEnglish.request();
        recyclerEnglish.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerEnglish.setHasFixedSize(true);
        recyclerEnglish.setItemAnimator(new DefaultItemAnimator());
        recyclerEnglish.setAdapter(adapterEnglish);

        adapterReasoning = new VideoRecyclerAdapter(this,"reasoning");
        adapterReasoning.request();
        recyclerReasoning.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerReasoning.setHasFixedSize(true);
        recyclerReasoning.setItemAnimator(new DefaultItemAnimator());
        recyclerReasoning.setAdapter(adapterReasoning);

        adapterGk = new VideoRecyclerAdapter(this,"gk");
        adapterGk.request();
        recyclerGk.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerGk.setHasFixedSize(true);
        recyclerGk.setItemAnimator(new DefaultItemAnimator());
        recyclerGk.setAdapter(adapterGk);
    }

    @Override
    public void showProgress(String link) {
        switch(link){
            case "popular":{
                progressBarPopular.setVisibility(View.VISIBLE);
                recyclerPopular.setVisibility(View.INVISIBLE);
                break;}
            case "math":{
                progressBarMath.setVisibility(View.VISIBLE);
                recyclerMath.setVisibility(View.INVISIBLE);
                break;}
            case "english":{
                progressBarEnglish.setVisibility(View.VISIBLE);
                recyclerEnglish.setVisibility(View.INVISIBLE);
                break;}
            case "reasoning":{
                progressBarReasoning.setVisibility(View.VISIBLE);
                recyclerReasoning.setVisibility(View.INVISIBLE);
                break;}
            case "gk":{
                progressBarGk.setVisibility(View.VISIBLE);
                recyclerGk.setVisibility(View.INVISIBLE);
                break;}
        }
    }

    @Override
    public void hideProgress(String link) {
        switch(link){
            case "popular":{
                progressBarPopular.setVisibility(View.INVISIBLE);
                recyclerPopular.setVisibility(View.VISIBLE);
                break;}
            case "math":{
                progressBarMath.setVisibility(View.INVISIBLE);
                recyclerMath.setVisibility(View.VISIBLE);
                break;}
            case "english":{
                progressBarEnglish.setVisibility(View.INVISIBLE);
                recyclerEnglish.setVisibility(View.VISIBLE);
                break;}
            case "reasoning":{
                progressBarReasoning.setVisibility(View.INVISIBLE);
                recyclerReasoning.setVisibility(View.VISIBLE);
                break;}
            case "gk":{
                progressBarGk.setVisibility(View.INVISIBLE);
                recyclerGk.setVisibility(View.VISIBLE);
                break;}
        }
    }

    @Override
    public void onClick(View v) {

    }
    public void startVideo(Video video){
        // inside your activity (if you did not enable transitions in your theme)
        // itemView.getContext().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        // set an exit transition
        //getWindow().setExitTransition(new Explode());
        // previously invisible view
        //View view = itemView.findViewById(R.id.card_view);
        //View view = itemView.getRootView().getRootView();
        // get the center for the clipping circle
        //int centerX = (view.getLeft() + view.getRight()) / 2;
        //int centerY = (view.getTop() + view.getBottom()) / 2;

        //int startRadius = 0;
        // get the final radius for the clipping circle
        //int endRadius = Math.max(view.getWidth(),view.getHeight());
        //Toast.makeText(itemView.getContext(),mVideoList.get(getAdapterPosition()).getHeader(),Toast.LENGTH_SHORT).show();
        Intent detail = new Intent(getActivity(), QuickPlayActivity.class);
        detail.putExtra("Video",video);
        // create the animator for this view (the start radius is zero)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            //itemView.getContext().startActivity(detail);
        }else{

        }
        //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) itemView.getContext(),mHeaderView,"heading");
        getActivity().startActivity(detail);

    }
}
