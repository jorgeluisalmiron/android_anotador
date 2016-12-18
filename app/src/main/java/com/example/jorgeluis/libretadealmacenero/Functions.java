package com.example.jorgeluis.libretadealmacenero;

import android.content.Context;
import android.os.Build;

/**
 * Created by Jorge Luis on 16/09/2016.
 */
public class Functions {

    public static int getColorWrapper(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(id);
        } else {
            //noinspection deprecation
            return context.getResources().getColor(id);
        }
    }
}
