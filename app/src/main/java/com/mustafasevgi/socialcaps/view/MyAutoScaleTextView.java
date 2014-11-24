package com.mustafasevgi.socialcaps.view;

import android.content.Context;
import android.util.AttributeSet;

public class MyAutoScaleTextView extends AutoScaleTextView {

    public MyAutoScaleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAutoScaleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth() / 4);
    }
}
