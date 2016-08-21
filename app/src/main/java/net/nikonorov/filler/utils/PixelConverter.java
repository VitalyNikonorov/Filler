package net.nikonorov.filler.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by vitaly on 21.08.16.
 */
public class PixelConverter {

    public static int DPToPX(int dp, Context context){

        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

}
