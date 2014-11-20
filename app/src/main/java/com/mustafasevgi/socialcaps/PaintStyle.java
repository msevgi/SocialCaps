// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.mustafasevgi.socialcaps;

import android.graphics.Paint;
import android.graphics.Typeface;

public class PaintStyle {

    public static int FONT_SIZE = 45;
    private Typeface font_typeface;
    private Paint mTextPaint;
    private Paint mTextPaintOutline;

    public PaintStyle(Typeface typeface) {
        font_typeface = typeface;
    }

    public Paint getFillPaint() {
        mTextPaint = new Paint();
        mTextPaint.setTypeface(font_typeface);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(FONT_SIZE);
        mTextPaint.setColor(-1);
        mTextPaint.setStyle(Paint.Style.FILL);
        return mTextPaint;
    }

    public Paint getStrokPaint() {
        mTextPaintOutline = new Paint();
        mTextPaintOutline.setTypeface(font_typeface);
        mTextPaintOutline.setAntiAlias(true);
        mTextPaintOutline.setTextSize(FONT_SIZE);
        mTextPaintOutline.setColor(0xff000000);
        mTextPaintOutline.setStyle(Paint.Style.STROKE);
        mTextPaintOutline.setStrokeWidth(4F);
        return mTextPaintOutline;
    }

}
