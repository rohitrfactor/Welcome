package com.pinnacle.garorasu.welcome.Login;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.pinnacle.garorasu.welcome.R;

import static com.pinnacle.garorasu.welcome.R.id.view_pager;

public class LoginUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        final HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.scroll_view);
        final ImageView imageView = (ImageView) findViewById(R.id.background);
        final ViewPager viewPager = (ViewPager) findViewById(view_pager);
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20*2,
                getResources().getDisplayMetrics());
        viewPager.setPageMargin(-margin);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int x = (int) ((viewPager.getWidth() * position + positionOffsetPixels) * computeFactor());
                scrollView.scrollTo(x, 0);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

            private float computeFactor() {
                return (imageView.getWidth() - viewPager.getWidth()) /
                        (float)(viewPager.getWidth() * (viewPager.getAdapter().getCount() - 1));
            }
        });
        viewPager.setAdapter(new CustomPagerAdapter(this));
    }
}
