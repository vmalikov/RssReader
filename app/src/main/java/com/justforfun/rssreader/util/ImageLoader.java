package com.justforfun.rssreader.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.justforfun.rssreader.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Vladimir on 5/25/17.
 */

public class ImageLoader {

    public interface OnDrawableReady {
        void onReady(Drawable drawable);
    }

    public static void loadLogo(Context context, String url, OnDrawableReady onDrawableReady) {
        if(onDrawableReady == null) throw new IllegalArgumentException("OnDrawableReady callback can not be null!");

        Picasso.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(getLogoTarget(context.getResources(), onDrawableReady));
    }

    private static Target getLogoTarget(Resources resources, OnDrawableReady onDrawableReady) {
        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Bitmap b = Bitmap.createScaledBitmap(bitmap, 120, 120, false);
                BitmapDrawable icon = new BitmapDrawable(resources, b);
                onDrawableReady.onReady(icon);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
    }

}
