package com.pinnacle.garorasu.welcome.Feed;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import com.pinnacle.garorasu.welcome.Downloads.DownloadActivity;
import com.pinnacle.garorasu.welcome.FeedDetail.FeedDetailActivity;
import com.pinnacle.garorasu.welcome.R;

import com.squareup.picasso.Picasso;

public class FeedFragment extends Fragment implements FeedView, View.OnClickListener{
    private ProgressBar progressBar;
    private RecyclerView recycler;
    private FeedRecyclerAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        TextView title = (TextView) view.findViewById(R.id.feed_fragment_header);
        TextView subtitle = (TextView) view.findViewById(R.id.feed_fragment_sub_header);
        Typeface custom_font = Typeface.createFromAsset(getResources().getAssets(),  "fonts/SpecialElite.ttf");
        title.setTypeface(custom_font);
        subtitle.setTypeface(custom_font);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_feed);
        recycler = (RecyclerView) view.findViewById(R.id.recycler_feed);
        recycler.setNestedScrollingEnabled(false);
        ImageView book = (ImageView) view.findViewById(R.id.feed_image);
        Picasso.with(getContext()).load(R.drawable.book_table).into(book);
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        fillUI();
    }

    public boolean  isConnected(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public void fillUI(){
        if(!isConnected(getContext())){
            Toast.makeText(getContext(),"Check your internet connection",Toast.LENGTH_SHORT).show();
        }
        adapter = new FeedRecyclerAdapter(this);
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
    public void startDetailScreen(Feed feed){
        // inside your activity (if you did not enable transitions in your theme)
        // itemView.getContext().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        // set an exit transition
        //getWindow().setExitTransition(new Explode());
        // previously invisible view
        //View view = itemView.findViewById(R.id.card_view);
        //View view = itemView.getRootView().getRootView();
        // get the center for the clipping circle
       // int centerX = (view.getLeft() + view.getRight()) / 2;
       // int centerY = (view.getTop() + view.getBottom()) / 2;

       // int startRadius = 0;
        // get the final radius for the clipping circle
       // int endRadius = Math.max(view.getWidth(),view.getHeight());
        //Toast.makeText(itemView.getContext(),mFeedList.get(getAdapterPosition()).getHeader(),Toast.LENGTH_SHORT).show();
        Intent detail = new Intent(getContext(), FeedDetailActivity.class);
        detail.putExtra("FEED",feed);
        // create the animator for this view (the start radius is zero)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            //itemView.getContext().startActivity(detail);
        }else{

        }
        //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),recycler,"heading");
        //getContext().startActivity(detail,options.toBundle());
        getContext().startActivity(detail);

    }

}
