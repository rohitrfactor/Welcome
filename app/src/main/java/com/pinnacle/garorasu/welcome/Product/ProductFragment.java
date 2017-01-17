package com.pinnacle.garorasu.welcome.Product;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import com.pinnacle.garorasu.welcome.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class ProductFragment extends Fragment implements ProductView {

    private CarouselView carouselView;
    private ProgressBar progressBar;
    private RecyclerView recycler;
    private ProductRecyclerAdapter adapter;
    int[] sampleImages = {R.drawable.image1,R.drawable.image2,R.drawable.image3,
            R.drawable.image4,R.drawable.image5};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        adapter = new ProductRecyclerAdapter(this);
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
        adapter.request();
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
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
            Intent detail = new Intent(getContext(), ProductDetail.class);
            detail.putExtra("PRODUCT",product);
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


