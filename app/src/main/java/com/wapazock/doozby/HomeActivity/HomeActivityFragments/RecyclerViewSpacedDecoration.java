package com.wapazock.doozby.HomeActivity.HomeActivityFragments;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewSpacedDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public RecyclerViewSpacedDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) <= 2) {
                outRect.top = space + 100;
            } else {
                outRect.top = 0;
            }
        }
}
