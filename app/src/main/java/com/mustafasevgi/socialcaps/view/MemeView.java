package com.mustafasevgi.socialcaps.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mustafasevgi.socialcaps.R;


@SuppressWarnings("UnusedDeclaration")
public class MemeView extends FrameLayout {
    private ImageView mImageView;
    private MyAutoScaleView mTextView;

    public MemeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            init();
    }

    public MemeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    private void init() {
        inflate(getContext(), R.layout.cell_meme, this);

        mImageView = (ImageView) findViewById(R.id.cell_meme_image);
        mTextView = (MyAutoScaleView) findViewById(R.id.cell_meme_textview);
    }

    public void setImage(@DrawableRes int drawableRes) {
        mImageView.setImageResource(drawableRes);
    }

    public void setText(CharSequence text) {
        mTextView.setText(text);
    }
}
