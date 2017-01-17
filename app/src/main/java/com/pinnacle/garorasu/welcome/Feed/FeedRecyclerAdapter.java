package com.pinnacle.garorasu.welcome.Feed;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinnacle.garorasu.welcome.R;

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
                presenter.startDetailScreen(mFeedList.get(getAdapterPosition()));
                }
            });
        }
    }
}
