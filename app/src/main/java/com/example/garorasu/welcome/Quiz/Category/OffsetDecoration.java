package com.example.garorasu.welcome.Quiz.Category;

/**
 * Created by garorasu on 2/12/16.
 */

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by garorasu on 1/12/16.
 */

public class OffsetDecoration extends RecyclerView.ItemDecoration {

    private final int mOffset;

    public OffsetDecoration(int offset) {
        mOffset = offset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = mOffset;
        outRect.right = mOffset;
        outRect.bottom = mOffset;
        outRect.top = mOffset;
    }
}