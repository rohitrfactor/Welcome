package com.example.garorasu.welcome.Feed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.garorasu.welcome.R;

import java.text.CollationElementIterator;
import java.util.ArrayList;

/**
 * Created by garorasu on 14/11/16.
 */

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder> implements FeedAdapterView{
    private final ArrayList<Feed> mFeedList = new ArrayList<>();
    private final FeedPresenter presenter;

     public FeedRecyclerAdapter(){
         presenter = new FeedPresenterImplementor(this);
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
        holder.mTextView.setText(current.getHeader());
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
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.feed_header);
        }
    }
}
