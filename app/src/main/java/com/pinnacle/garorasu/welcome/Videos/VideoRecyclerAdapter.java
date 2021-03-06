package com.pinnacle.garorasu.welcome.Videos;

import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.pinnacle.garorasu.welcome.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by garorasu on 21/11/16.
 */

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder> implements VideoAdapterView{
    private final ArrayList<Video> mVideoList = new ArrayList<>();
    private final VideoPresenter presenter;
    private String link;


    public VideoRecyclerAdapter(VideoView view,String link){
        presenter = new VideoPresenterImplementor(this,view);
        this.link = link;
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
        String url = "https://img.youtube.com/vi/"+current.getVid()+"/3.jpg";
        System.out.println(url);
        Picasso.with(holder.mVideoView.getContext()).load(url).into(holder.mVideoView);
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
        presenter.requestVideos(link);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mHeaderView;
        public CardView mCardView;
        public ImageView mVideoView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mHeaderView = (TextView) itemView.findViewById(R.id.video_header);
            mVideoView = (ImageView) itemView.findViewById(R.id.video_image);
            Typeface custom_font_1 = Typeface.createFromAsset(mHeaderView.getContext().getAssets(),  "fonts/Nunito-Regular.ttf");
            mHeaderView.setTypeface(custom_font_1);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                presenter.startVideo(mVideoList.get(getAdapterPosition()));
                }
            });
        }
    }
}
