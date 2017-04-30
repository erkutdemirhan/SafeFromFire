package com.sparky.safefromfire.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.FileNotFoundException;

/**
 * Created by Erkut Demirhan on 30/04/17.
 */
public class BitmapUtils {

    private static final int SCALE_FACTOR = 900;

    public static Bitmap decodeUri(Context context, Uri imageUri) throws FileNotFoundException {
        Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(imageUri));
        int newHeight;
        int newWidth;
        if(bitmap.getHeight() > bitmap.getWidth()) {
            newHeight = SCALE_FACTOR;
            newWidth  = (int) (bitmap.getWidth() * ((float) SCALE_FACTOR / bitmap.getHeight()));
        } else {
            newHeight = (int) (bitmap.getHeight() * ((float) SCALE_FACTOR / bitmap.getWidth()));
            newWidth  = SCALE_FACTOR;
        }
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
        bitmap.recycle();
        return scaledBitmap;
    }
}
