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
import android.view.View;
import android.widget.EditText;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.mustafasevgi.socialcaps.PaintStyle;
import com.mustafasevgi.socialcaps.R;
import com.mustafasevgi.socialcaps.event.CapsImageClickModel;
import com.mustafasevgi.socialcaps.view.CapsView;
import com.soundcloud.android.crop.Crop;
import com.squareup.otto.Subscribe;

import java.io.File;

public class CreateCapsActivity extends BaseActionBarActivity implements TextWatcher {
    private EditText editTextCaption;
    private Uri outputUri;

    private Uri source1;
    private CapsView capsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_create_caps_toolbar);
        setSupportActionBar(toolbar);

        capsView = (CapsView) findViewById(R.id.capsview);
//        Crop.pickImage(CreateCapsActivity.this);
        editTextCaption = (EditText) findViewById(R.id.edittext_caps);
        editTextCaption.addTextChangedListener(this);
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


    private Bitmap processingBitmap() {
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
        new Crop(source).output(outputUri).start(CreateCapsActivity.this);
    }

    private void handleCrop(int resultCode) {
        if (resultCode == RESULT_OK && outputUri != null) {
            source1 = outputUri;
            capsView.setImageUri(outputUri);
        }
    }

    @Subscribe
    public void capsImageClick(CapsImageClickModel model) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        dialogBuilder
                .withTitle("Info")                                  //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000").withMessage(null)                             //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)
                .withIcon(getResources().getDrawable(R.drawable.left_menu_plus))
                .withDuration(700)                                          //def
                .withEffect(Effectstype.Slidetop)                                         //def Effectstype.Slidetop
                .withButton1Text("OK")                                      //def gone
                .withButton2Text("Cancel")                                  //def gone
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .setCustomView(R.layout.custom_alert_view, getApplicationContext())         //.setCustomView(View or ResId,context)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Crop.pickImage(CreateCapsActivity.this);
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                })
                .show();

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Bitmap processedBitmap = processingBitmap();
        if (processedBitmap != null) {
            capsView.setImageBitmap(processedBitmap);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}