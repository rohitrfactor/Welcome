package com.example.garorasu.welcome.Videos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.garorasu.welcome.R;

import java.util.ArrayList;

/**
 * Created by garorasu on 21/11/16.
 */

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder> implements VideoAdapterView{
    private final ArrayList<Video> mVideoList = new ArrayList<>();
    private final VideoPresenter presenter;


    public VideoRecyclerAdapter(VideoView view){
        presenter = new VideoPresenterImplementor(this,view);
        System.out.println("Video recycler Adapter Constructor called");
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Video current = mVideoList.get(position);
        holder.mHeaderView.setText(current.getHeader());
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }
    @Override
    public void addItem(Video message) {
        System.out.println("New Content added");
        mVideoList.add(message);
        notifyDataSetChanged();

    }
    public void request() {
        System.out.println("Request to new messages sent");
        presenter.requestVideos();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mHeaderView,mPreviewView,mDurationView,mContinueReading;
        //public CardView mCardView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mHeaderView = (TextView) itemView.findViewById(R.id.video_header);
            Typeface custom_font_1 = Typeface.createFromAsset(mHeaderView.getContext().getAssets(),  "fonts/Nunito-Regular.ttf");
            mHeaderView.setTypeface(custom_font_1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                presenter.startVideo(mVideoList.get(getAdapterPosition()));
                }
            });
        }
    }
}
