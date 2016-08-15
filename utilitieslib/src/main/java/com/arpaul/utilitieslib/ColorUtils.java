package com.arpaul.utilitieslib;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Created by Aritra on 5/20/2016.
 */
public class ColorUtils {
    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static Drawable getColoredDrawable(Context context, int drawable, int color){
        Drawable mDrawable = ResourcesCompat.getDrawable(context.getResources(), drawable, null);
        mDrawable.setColorFilter(new PorterDuffColorFilter(getColor(context, color), PorterDuff.Mode.MULTIPLY));

        return mDrawable;
    }
}
