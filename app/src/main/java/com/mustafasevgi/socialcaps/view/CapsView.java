package com.mustafasevgi.socialcaps.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mustafasevgi.socialcaps.R;
import com.mustafasevgi.socialcaps.event.CapsImageClickModel;
import com.mustafasevgi.socialcaps.provider.BusProvider;


@SuppressWarnings("UnusedDeclaration")
public class CapsView extends FrameLayout implements View.OnClickListener {

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
        inflate(getContext(), R.layout.caps_view, this);

        imageView = (ImageView) findViewById(R.id.imageview_caps);
        imageView.setOnClickListener(this);
        textView = (MyAutoScaleTextView) findViewById(R.id.textview_caps);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_caps:
                BusProvider.getInstance().post(new CapsImageClickModel());
                break;
        }
    }
}
