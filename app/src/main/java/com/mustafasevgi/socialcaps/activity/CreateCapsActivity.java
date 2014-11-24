package com.mustafasevgi.socialcaps.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.mustafasevgi.socialcaps.PaintStyle;
import com.mustafasevgi.socialcaps.R;
import com.mustafasevgi.socialcaps.view.CapsView;
import com.soundcloud.android.crop.Crop;

import java.io.File;

public class CreateCapsActivity extends BaseActionBarActivity {
    private EditText editTextCaption;
    private Uri outputUri;

    Uri source1;
    CapsView capsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_create_caps_toolbar);
        setSupportActionBar(toolbar);

        capsView = (CapsView) findViewById(R.id.capsview);
//        Crop.pickImage(CreateCapsActivity.this);
        editTextCaption = (EditText) findViewById(R.id.caption);
        editTextCaption.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                Bitmap processedBitmap = ProcessingBitmap();
                if (processedBitmap != null) {
                    capsView.setImageBitmap(processedBitmap);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getLayoutResource() {
        return R.layout.layout_create;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 9162:
                    beginCrop(data.getData());
                    break;
                case 6709:
                    handleCrop(resultCode);
                    break;
            }
        }
    }


    private Bitmap ProcessingBitmap() {
        Bitmap bitmap = null;
        try {
            Bitmap meme_image = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(source1));

            String captionString = editTextCaption.getText().toString();
            if (captionString != null) {

                capsView.setText(captionString);

            }
            Typeface typeface = Typeface.createFromAsset(getAssets(),
                    "androidnation.ttf");
            PaintStyle paintstyle = new PaintStyle(typeface);

            capsView.buildDrawingCache();


            Bitmap bitmapEditText = Bitmap.createScaledBitmap(capsView.getDrawingCache(), capsView.getWidth(), capsView.getWidth() / 4, true);
            Paint mTextPaintOutline = paintstyle.getStrokPaint();
            bitmap = Bitmap.createBitmap(meme_image.getWidth(), meme_image.getHeight(), meme_image.getConfig());
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(meme_image, 0.0F, 0.0F, new Paint());
            canvas.drawBitmap(bitmapEditText, 0, meme_image.getHeight() - (meme_image.getHeight() / 4), mTextPaintOutline);
            capsView.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private void beginCrop(Uri source) {
        outputUri = Uri.fromFile(new File(getCacheDir(), "cropped"));
        new Crop(source).output(outputUri).asSquare().start(CreateCapsActivity.this);
    }

    private void handleCrop(int resultCode) {
        if (resultCode == RESULT_OK && outputUri != null) {
            source1 = outputUri;
            capsView.setImageUri(outputUri);
        }
    }
}