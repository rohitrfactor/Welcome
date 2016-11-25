package com.example.garorasu.welcome.Product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.garorasu.welcome.FeedDetail.FeedDetailActivity;
import com.example.garorasu.welcome.R;
import com.example.garorasu.welcome.Videos.VideoRecyclerAdapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class ProductFragment extends Fragment implements ProductView {

    private CarouselView carouselView;
    private ProgressBar progressBar;
    private RecyclerView recycler;
    private ProductRecyclerAdapter adapter;
    int[] sampleImages = {R.drawable.testbook,R.drawable.book_grass,R.drawable.product,R.drawable.laptop};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_product);
        recycler = (RecyclerView) view.findViewById(R.id.recycler_product);
        fillUI();
        return view;
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    public void fillUI(){
        adapter = new ProductRecyclerAdapter(this);
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
    public void gotoProduct(Product product){
        //Pass an intent to WebView Activity
        //Intent detail = new Intent(getActivity(), FeedDetailActivity.class);
        //detail.putExtra("Video",video);
        // create the animator for this view (the start radius is zero)
        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            //itemView.getContext().startActivity(detail);
        //}else{

        //}
        //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) itemView.getContext(),mHeaderView,"heading");
        //getActivity().startActivity(detail);

    }
}


