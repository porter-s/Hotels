package ru.example.user.hotels.methods;

import android.graphics.Bitmap;

/**
 * Created by user on 29.01.15.
 */
public class Cropping
{
    public static Bitmap crop(Bitmap paramBitmap)
    {
        if (paramBitmap != null)
        {
            int i = paramBitmap.getWidth();
            int j = paramBitmap.getHeight();
            int k = i - 2;
            int m = j - 2;
            Bitmap localBitmap = Bitmap.createBitmap(k, m, Bitmap.Config.ARGB_8888);
            int[] arrayOfInt = new int[k * m];
            paramBitmap.getPixels(arrayOfInt, 0, k, 1, 1, k, m);
            localBitmap.setPixels(arrayOfInt, 0, k, 0, 0, k, m);
            return localBitmap;
        }
        return null;
    }
}