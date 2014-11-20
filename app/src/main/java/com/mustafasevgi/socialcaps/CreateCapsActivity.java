package com.mustafasevgi.socialcaps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.FileNotFoundException;

public class CreateCapsActivity extends BaseActionBarActivity {

    private Button btnLoadImage1;
    private TextView textSource1;
    private EditText editTextCaption;
    private Button btnProcessing;
    private ImageView imageResult;
    private AutoScaleTextView textResult;
    private Uri outputUri;

    final int REQUEST_PICK = Crop.REQUEST_PICK;
    final int REQUEST_CROP = Crop.REQUEST_CROP;

    Uri source1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create);

        btnLoadImage1 = (Button) findViewById(R.id.loadimage1);
        textSource1 = (TextView) findViewById(R.id.sourceuri1);
        editTextCaption = (EditText) findViewById(R.id.caption);
        btnProcessing = (Button) findViewById(R.id.processing);
        imageResult = (ImageView) findViewById(R.id.result);

        textResult = (AutoScaleTextView) findViewById(R.id.resulttext);

        btnLoadImage1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Crop.pickImage(CreateCapsActivity.this);
            }
        });

        btnProcessing.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (source1 != null) {
                    Bitmap processedBitmap = ProcessingBitmap();
                    if (processedBitmap != null) {
                        imageResult.setImageBitmap(processedBitmap);
                        Toast.makeText(getApplicationContext(),
                                "Done",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Something wrong in processing!",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Select both image!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 9162:
                    beginCrop(data.getData());
                    textSource1.setText(data.getData().toString());
                    break;
                case 6709:
                    handleCrop(resultCode);
                    break;
            }
        }
    }

    private Bitmap ProcessingBitmap() {
        Bitmap meme_image = null;
        Bitmap bitmap = null;
        try {
            meme_image = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(source1));

            String captionString = editTextCaption.getText().toString();
            if (captionString != null) {

                textResult.setText(captionString);

            } else {
                Toast.makeText(getApplicationContext(),
                        "caption empty!",
                        Toast.LENGTH_LONG).show();
            }
            Typeface typeface = Typeface.createFromAsset(getAssets(),
                    "androidnation.ttf");
            Paint mTextPaintOutline;
            PaintStyle paintstyle = new PaintStyle(typeface);
            textResult.buildDrawingCache();
            Bitmap bitmapEditText = Bitmap.createScaledBitmap(textResult.getDrawingCache(), meme_image.getWidth(), meme_image.getHeight() / 4, true);
            mTextPaintOutline = paintstyle.getStrokPaint();
            bitmap = Bitmap.createBitmap(meme_image.getWidth(), meme_image.getHeight(), meme_image.getConfig());
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(meme_image, 0.0F, 0.0F, new Paint());
            canvas.drawBitmap(bitmapEditText, 0, meme_image.getHeight() - (meme_image.getHeight() / 4), mTextPaintOutline);
            textResult.setVisibility(View.GONE);
            imageResult.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bitmap;
    }

    public int xBound(float f, Bitmap meme_image) {
        return (int) (((float) meme_image.getWidth() - f) / 2.0F);
    }

    public int yBound(Bitmap meme_image) {
        return -6 + meme_image.getHeight();
    }

    private void beginCrop(Uri source) {
        outputUri = Uri.fromFile(new File(getCacheDir(), "cropped"));
        new Crop(source).output(outputUri).asSquare().start(CreateCapsActivity.this);
    }

    private void handleCrop(int resultCode) {
        if (resultCode == RESULT_OK && outputUri != null) {
            source1 = outputUri;
        }
    }
}