package com.example.garorasu.welcome.Feed;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.garorasu.welcome.R;

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
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        fillUI();
        return view;
    }

    public void fillUI(){
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
}
