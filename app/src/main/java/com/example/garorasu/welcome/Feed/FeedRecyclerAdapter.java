package com.example.garorasu.welcome.Feed;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.garorasu.welcome.FeedDetail.FeedDetailActivity;
import com.example.garorasu.welcome.R;

import java.text.CollationElementIterator;
import java.util.ArrayList;

/**
 * Created by garorasu on 14/11/16.
 */

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder> implements FeedAdapterView{
    private final ArrayList<Feed> mFeedList = new ArrayList<>();
    private final FeedPresenter presenter;


     public FeedRecyclerAdapter(FeedView view){
         presenter = new FeedPresenterImplementor(this,view);
         System.out.println("Feed recycler Adapter Constructor called");
     }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Feed current = mFeedList.get(position);
        holder.mHeaderView.setText(current.getHeader());
        holder.mPreviewView.setText(current.getPreviewContent());
        holder.mDurationView.setText(current.getDuration());
        holder.mContinueReading.setText("Continue reading ...");

    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }
    @Override
    public void addItem(Feed message) {
        System.out.println("New Content added");
        mFeedList.add(message);
        notifyDataSetChanged();

    }
    public void request() {
        System.out.println("Request to new messages sent");
        presenter.requestMessages();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mHeaderView,mPreviewView,mDurationView,mContinueReading;
        //public CardView mCardView;

        public ViewHolder(final View itemView) {
            super(itemView);
            //mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mHeaderView = (TextView) itemView.findViewById(R.id.feed_header);
            mPreviewView = (TextView) itemView.findViewById(R.id.feed_preview);
            mDurationView = (TextView) itemView.findViewById(R.id.feed_duration);
            mContinueReading = (TextView) itemView.findViewById(R.id.continue_reading);
            Typeface custom_font_1 = Typeface.createFromAsset(mHeaderView.getContext().getAssets(),  "fonts/Nunito-Regular.ttf");
            mHeaderView.setTypeface(custom_font_1);
            Typeface custom_font_2 = Typeface.createFromAsset(mPreviewView.getContext().getAssets(),  "fonts/NixieOne-Regular.ttf");
            mPreviewView.setTypeface(custom_font_2);
            mDurationView.setTypeface(custom_font_2);
            mContinueReading.setTypeface(custom_font_2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // inside your activity (if you did not enable transitions in your theme)
                    // itemView.getContext().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

                    // set an exit transition
                    //getWindow().setExitTransition(new Explode());
                    // previously invisible view
                    //View view = itemView.findViewById(R.id.card_view);
                    View view = itemView.getRootView().getRootView();
// get the center for the clipping circle
                    int centerX = (view.getLeft() + view.getRight()) / 2;
                    int centerY = (view.getTop() + view.getBottom()) / 2;

                    int startRadius = 0;
// get the final radius for the clipping circle
                    int endRadius = Math.max(view.getWidth(),view.getHeight());
                    //Toast.makeText(itemView.getContext(),mFeedList.get(getAdapterPosition()).getHeader(),Toast.LENGTH_SHORT).show();
                    Intent detail = new Intent(itemView.getContext(), FeedDetailActivity.class);
                    detail.putExtra("FEED",mFeedList.get(getAdapterPosition()));
// create the animator for this view (the start radius is zero)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                                //itemView.getContext().startActivity(detail);
                    }else{

                    }
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) itemView.getContext(),mHeaderView,"heading");
                    itemView.getContext().startActivity(detail,options.toBundle());


                }
            });
        }
    }
}
