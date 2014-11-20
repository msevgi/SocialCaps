package com.mustafasevgi.socialcaps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.FileNotFoundException;

public class CreateActivity extends BaseActionBarActivity implements TextWatcher {
    private ImageView imageViewCaps;
    private EditText editTextCaps;

    private Uri outputUri;
    private Uri source1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create_caps);
        imageViewCaps = (ImageView) findViewById(R.id.imageview_caps);
        editTextCaps = (EditText) findViewById(R.id.edittext_caps);
        Crop.pickImage(CreateActivity.this);
        editTextCaps.addTextChangedListener(this);
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
                default:
                    break;
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        processingBitmap();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void beginCrop(Uri source) {
        outputUri = Uri.fromFile(new File(getCacheDir(), "cropped"));
        new Crop(source).output(outputUri).asSquare().start(CreateActivity.this);
    }

    private void handleCrop(int resultCode) {
        if (resultCode == RESULT_OK && outputUri != null) {
            source1 = outputUri;
            processingBitmap();
//            try {
//                imageViewCaps.setImageBitmap(BitmapFactory.decodeStream(
//                        getContentResolver().openInputStream(source1)));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
        }
    }

    private Bitmap processingBitmap() {
        Bitmap bitmapImage = null;
        String capsText = editTextCaps.getText().toString();

//        if (capsText == null)
//            return bitmapImage;

//        editTextCaps.setCursorVisible(false);
//        editTextCaps.buildDrawingCache();
//        Bitmap bitmapEditText = Bitmap.createBitmap(editTextCaps.getDrawingCache());

        try {
            bitmapImage = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(source1));
            Typeface custom_font = Typeface.createFromAsset(getAssets(),
                    "androidnation.ttf");
            HandleMemeWriteBounds handleMemeWriteBounds = new HandleMemeWriteBounds(bitmapImage, custom_font);
            Bitmap bitmap = handleMemeWriteBounds.generateImage("ds", "fge");
            imageViewCaps.setImageBitmap(bitmap);
//            Canvas comboImage = new Canvas(bitmapImage);
//            comboImage.drawBitmap(bitmapImage, 0f, 0f, null);
//            comboImage.drawBitmap(bitmapEditText, 0f, bitmapImage.getHeight() - bitmapEditText.getHeight(), null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bitmapImage;
    }
}
