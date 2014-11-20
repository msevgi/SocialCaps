package com.mustafasevgi.socialcaps;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package org.aynsoft.views:
//            PaintStyle

public class HandleMemeWriteBounds {

    int BOTTOM_TEXT_LENGHT;
    int FONT_SIZE;
    int TOP_TEXT_LENGHT;
    Paint mTextPaint;
    Paint mTextPaintOutline;
    Bitmap meme_image;
    int width;

    public HandleMemeWriteBounds(Bitmap bitmap, Typeface typeface) {
        FONT_SIZE = PaintStyle.FONT_SIZE;
        PaintStyle paintstyle = new PaintStyle(typeface);
        mTextPaint = paintstyle.getFillPaint();
        mTextPaintOutline = paintstyle.getStrokPaint();
        meme_image = bitmap;
    }

    public Bitmap generateImage(String top_text, String bottom_text) {
        TOP_TEXT_LENGHT = top_text.length();
        BOTTOM_TEXT_LENGHT = bottom_text.length();
        Bitmap bitmap = Bitmap.createBitmap(meme_image.getWidth(), meme_image.getHeight(), meme_image.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(meme_image, 0.0F, 0.0F, new Paint());
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        Path mPath = new Path();

        RectF mRectF = new RectF(0, meme_image.getHeight(), meme_image.getWidth(), meme_image.getHeight());
        mPath.addRect(mRectF, Path.Direction.CCW);
        mPaint.setStrokeWidth(200);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mPath, mPaint);
        mTextPaintOutline.setColor(Color.WHITE);
        canvas.drawText("mustafavnxvnfvg fdbnfdzn  hgvvghbhbhjbvdhhghghgfxhjgxj hj xjjxfg", xBound(mTextPaintOutline.measureText("mustafavnxvnfvg fdbnfdzn  hgvvghbhbhjbvdhhghghgfxhjgxj hj xjjxfg")), yBound(), mTextPaintOutline);
//        writeOnTop(top_text, mTextPaintOutline, canvas);
//        writeOnTop(top_text, mTextPaint, canvas);
//        writeOnBottom(bottom_text, mTextPaintOutline, canvas);
//        writeOnBottom(bottom_text, mTextPaint, canvas);
        return bitmap;
    }

    //    public void writeOnBottom(String s, Paint paint, Canvas canvas)
//    {
//        String s1;
//        ArrayList arraylist;
//        s1 = s;
//        arraylist = new ArrayList();
//        _L3:
//        if (!s1.isEmpty()) goto _L2; else goto _L1
//        _L1:
//        Rect rect;
//        int i;
//        int j;
//        Iterator iterator;
//        rect = new Rect();
//        i = -10 + canvas.getHeight();
//        j = 1;
//        iterator = arraylist.iterator();
//        _L4:
//        if (!iterator.hasNext())
//        {
//            return;
//        }
//        break MISSING_BLOCK_LABEL_107;
//        _L2:
//        int k = paint.breakText(s1, true, (float)canvas.getWidth() - (float)3, null);
//        arraylist.add(s1.substring(0, k));
//        s1 = s1.substring(k);
//        goto _L3
//        String s2 = (String)iterator.next();
//        float f = i - (arraylist.size() - j) * FONT_SIZE;
//        canvas.drawText(s2, xBound(paint.measureText(s2)), f, paint);
//        paint.getTextBounds(s2, 0, s2.length(), rect);
//        j++;
//        goto _L4
//    }
//
//    public void writeOnTop(String s, Paint paint, Canvas canvas)
//    {
//        String s1;
//        ArrayList arraylist;
//        s1 = s;
//        arraylist = new ArrayList();
//        _L3:
//        if (!s1.isEmpty()) goto _L2; else goto _L1
//        _L1:
//        Rect rect;
//        int i;
//        Iterator iterator;
//        rect = new Rect();
//        i = 3;
//        iterator = arraylist.iterator();
//        _L4:
//        if (!iterator.hasNext())
//        {
//            return;
//        }
//        break MISSING_BLOCK_LABEL_98;
//        _L2:
//        int j = paint.breakText(s1, true, (float)canvas.getWidth() - (float)3, null);
//        arraylist.add(s1.substring(0, j));
//        s1 = s1.substring(j);
//        goto _L3
//        String s2 = (String)iterator.next();
//        canvas.drawText(s2, xBound(paint.measureText(s2)), i + FONT_SIZE, paint);
//        paint.getTextBounds(s2, 0, s2.length(), rect);
//        i = 10 + (i + rect.height());
//        goto _L4
//    }
//
    public int xBound(float f) {
        return (int) (((float) meme_image.getWidth() - f) / 2.0F);
    }

    public int yBound() {
        return -6 + meme_image.getHeight();
    }
}
