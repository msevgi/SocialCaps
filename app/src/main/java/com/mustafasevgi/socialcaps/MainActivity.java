package com.mustafasevgi.socialcaps;

import java.io.File;
import java.io.FileNotFoundException;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

public class MainActivity extends Activity {

    Button btnLoadImage1;
    TextView textSource1;
    EditText editTextCaption;
    Button btnProcessing;
    ImageView imageResult;
    AutoScaleTextView textResult;
    Uri outputUri;

    final int REQUEST_PICK = Crop.REQUEST_PICK;
    final int REQUEST_CROP = Crop.REQUEST_CROP;

    Uri source1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoadImage1 = (Button) findViewById(R.id.loadimage1);
        textSource1 = (TextView) findViewById(R.id.sourceuri1);
        editTextCaption = (EditText) findViewById(R.id.caption);
        btnProcessing = (Button) findViewById(R.id.processing);
        imageResult = (ImageView) findViewById(R.id.result);

        textResult = (AutoScaleTextView) findViewById(R.id.resulttext);

        btnLoadImage1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Crop.pickImage(MainActivity.this);
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
        Bitmap newBitmap = null;

        try {
            newBitmap = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(source1));

            String captionString = editTextCaption.getText().toString();
            if (captionString != null) {

                textResult.setText(captionString);

            } else {
                Toast.makeText(getApplicationContext(),
                        "caption empty!",
                        Toast.LENGTH_LONG).show();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return newBitmap;
    }

    private void beginCrop(Uri source) {
        outputUri = Uri.fromFile(new File(getCacheDir(), "cropped"));
        new Crop(source).output(outputUri).asSquare().start(MainActivity.this);
    }

    private void handleCrop(int resultCode) {
        if (resultCode == RESULT_OK && outputUri != null) {
            source1 = outputUri;
        }
    }
}