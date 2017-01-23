package com.pinnacle.garorasu.welcome.Downloads;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.pinnacle.garorasu.welcome.R;

import java.util.ArrayList;

/**
 * Created by garorasu on 19/1/17.
 */

public class DownloadRecyclerAdapter extends RecyclerView.Adapter<com.pinnacle.garorasu.welcome.Downloads.DownloadRecyclerAdapter.ViewHolder> implements DownloadAdapterView {
        private final ArrayList<Download> mDownloadList = new ArrayList<>();
        private final DownloadPresenter presenter;


        public DownloadRecyclerAdapter(DownloadView view){
            presenter = new DownloadPresenterImplementor(this,view);
            System.out.println("Download recycler Adapter Constructor called");
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.download_card, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Download current = mDownloadList.get(position);
            holder.mTitleView.setText(current.getTitle());
            holder.mDescriptionView.setText(current.getDescription());
            holder.mUpdate.setText(current.getUpdate());
        }

        @Override
        public int getItemCount() {
            return mDownloadList.size();
        }
        @Override
        public void addItem(Download message) {
            System.out.println("New Content added");
            mDownloadList.add(message);
            notifyDataSetChanged();
        }
        public void request() {
            System.out.println("Request to new messages sent");
            presenter.requestMessages();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTitleView,mDescriptionView,mUpdate;
            //public CardView mCardView;

            public ViewHolder(final View itemView) {
                super(itemView);
                //mCardView = (CardView) itemView.findViewById(R.id.card_view);
                mTitleView = (TextView) itemView.findViewById(R.id.download_title);
                mDescriptionView = (TextView) itemView.findViewById(R.id.download_description);
                mUpdate = (TextView) itemView.findViewById(R.id.download_update);
                Typeface custom_font_1 = Typeface.createFromAsset(mTitleView.getContext().getAssets(),  "fonts/Nunito-Regular.ttf");
                mTitleView.setTypeface(custom_font_1);
                mDescriptionView.setTypeface(custom_font_1);
                mUpdate.setTypeface(custom_font_1);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.startDownload(mDownloadList.get(getAdapterPosition()));
                    }
                });
            }
        }
}
