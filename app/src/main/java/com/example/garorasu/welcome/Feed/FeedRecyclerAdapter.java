package com.example.garorasu.welcome.Feed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        public TextView mHeaderView,mPreviewView,mDurationView;


        public ViewHolder(final View itemView) {
            super(itemView);

            mHeaderView = (TextView) itemView.findViewById(R.id.feed_header);
            mPreviewView = (TextView) itemView.findViewById(R.id.feed_preview);
            mDurationView = (TextView) itemView.findViewById(R.id.feed_duration);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),mFeedList.get(getAdapterPosition()).getHeader(),Toast.LENGTH_SHORT).show();
                    Intent detail = new Intent(itemView.getContext(), FeedDetailActivity.class);
                    detail.putExtra("FEED",mFeedList.get(getAdapterPosition()));
                    itemView.getContext().startActivity(detail);
                }
            });
        }
    }
}
