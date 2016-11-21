package com.example.garorasu.welcome.Videos;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.garorasu.welcome.Feed.FeedRecyclerAdapter;
import com.example.garorasu.welcome.Login.LoginActivity;
import com.example.garorasu.welcome.Main.MainActivity;
import com.example.garorasu.welcome.R;

public class VideoFragment extends Fragment implements VideoView,View.OnClickListener  {
    private ProgressBar progressBar;
    private RecyclerView recycler;
    private VideoRecyclerAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_video);
        recycler = (RecyclerView) view.findViewById(R.id.recycler_video);
        fillUI();
        return view;
    }
    public void fillUI(){
        adapter = new VideoRecyclerAdapter(this);
        adapter.request();
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
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
