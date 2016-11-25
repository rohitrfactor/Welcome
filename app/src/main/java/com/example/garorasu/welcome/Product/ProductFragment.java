package com.example.garorasu.welcome.Product;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.garorasu.welcome.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class ProductFragment extends Fragment implements ProductView {

    private CarouselView carouselView;
    int[] sampleImages = {R.drawable.testbook,R.drawable.book_grass,R.drawable.product,R.drawable.laptop};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        return view;
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    public void showProgress() {
        //progressBar.setVisibility(View.VISIBLE);
        //recycler.setVisibility(View.INVISIBLE);
    }

    public void hideProgress() {
        //progressBar.setVisibility(View.INVISIBLE);
        //recycler.setVisibility(View.VISIBLE);
    }
    public void gotoProduct(Product product){
        //Pass an intent to WebView Activity
    }

}
