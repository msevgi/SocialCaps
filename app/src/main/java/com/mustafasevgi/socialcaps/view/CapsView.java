package com.mustafasevgi.socialcaps.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mustafasevgi.socialcaps.R;


@SuppressWarnings("UnusedDeclaration")
public class CapsView extends FrameLayout {

    private ImageView imageView;
    private MyAutoScaleTextView textView;

    public CapsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            init();
    }

    public CapsView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        inflate(getContext(), R.layout.meme_view, this);

        imageView = (ImageView) findViewById(R.id.imageview_meme);
        textView = (MyAutoScaleTextView) findViewById(R.id.textview_meme);
    }

    public void setImageBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    public void setImageUri(Uri uri) {
        imageView.setImageURI(uri);
    }

    public void buildDrawingCache() {
        textView.buildDrawingCache();
    }

    public Bitmap getDrawingCache() {
        return textView.getDrawingCache();
    }

    public void setText(CharSequence text) {
        textView.setText(text);
    }
}
