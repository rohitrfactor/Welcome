package com.pinnacle.garorasu.welcome.Product;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pinnacle.garorasu.welcome.R;

import java.util.ArrayList;

/**
 * Created by garorasu on 25/11/16.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> implements ProductAdapterView{
    private final ArrayList<Product> mProductList = new ArrayList<>();
    private final ProductPresenter presenter;


    public ProductRecyclerAdapter(ProductView view){
        presenter = new ProductPresenterImplementor(this,view);
        System.out.println("Product recycler Adapter Constructor called");
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product current = mProductList.get(position);
        holder.mHeaderView.setText(current.getTitle());
        holder.mPriceView.setText(current.getPrice());
        holder.mDescripionView.setText(current.getDescription());
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }
    @Override
    public void addItem(Product message) {
        System.out.println("New Content added");
        mProductList.add(message);
        notifyDataSetChanged();

    }
    public void request() {
        System.out.println("Request to new products sent");
        presenter.requestProducts();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mHeaderView,mPriceView,mBuy,mDescripionView;
        //public CardView mCardView;
        public ImageView mProductView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mHeaderView = (TextView) itemView.findViewById(R.id.product_header);
            mDescripionView = (TextView) itemView.findViewById(R.id.product_description);
            mPriceView = (TextView) itemView.findViewById(R.id.product_price);
            mBuy = (TextView) itemView.findViewById(R.id.buy);
            Typeface custom_font_1 = Typeface.createFromAsset(mHeaderView.getContext().getAssets(),  "fonts/Nunito-Regular.ttf");
            Typeface custom_font_2 = Typeface.createFromAsset(mHeaderView.getContext().getAssets(),  "fonts/NixieOne-Regular.ttf");
            mHeaderView.setTypeface(custom_font_1);
            mPriceView.setTypeface(custom_font_1);
            mHeaderView.setTypeface(custom_font_1);
            mDescripionView.setTypeface(custom_font_2);
            mBuy.setTypeface(custom_font_1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.startProduct(mProductList.get(getAdapterPosition()));
                }
            });
        }
    }
}
