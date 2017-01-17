package com.pinnacle.garorasu.welcome.Login;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pinnacle.garorasu.welcome.R;

/**
 * Created by garorasu on 26/11/16.
 */
public class CustomPagerAdapter extends PagerAdapter {
    private String[] mBlueLineStationList = {"GJU Gate","Auto Market","Bus Stand","Nagori Gate",
            "Aggarsain Bhawan","Rani Laxmi Bai Chowk","Fawara Chowk","Camp Chowk","Town Park",
            "Dabra Chowk","Metropolis Mall","Satrod Khas"};
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public CustomPagerAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mBlueLineStationList.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        TextView textView = (TextView) itemView.findViewById(R.id.textView);
        textView.setText(mBlueLineStationList[position]);
        Typeface custom_font_1 = Typeface.createFromAsset(textView.getContext().getAssets(),  "fonts/Nunito-Regular.ttf");
        textView.setTypeface(custom_font_1);
        container.addView(itemView);

        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
