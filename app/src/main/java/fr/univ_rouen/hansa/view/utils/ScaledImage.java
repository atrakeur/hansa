package fr.univ_rouen.hansa.view.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ScaledImage {

    private float scaleWidth;
    private float scaleHeight;

    private Resources resources;
    private int resID;

    private int destWidth;
    private int destHeight;
    private Bitmap destBitmap;

    /**
     * Create a new ScaledImage according to a bitmap and a relative size to screen
     * @param scaleWidth the percentage taken on screen
     * @param scaleHeigth the percentage taken on screen
     */
    public ScaledImage(Resources res, int resID, float scaleWidth, float scaleHeigth) {
        this.resources = res;
        this.resID = resID;

        this.scaleWidth = scaleWidth;
        this.scaleHeight = scaleHeigth;
    }

    public Bitmap getScaledBitmap() {
        if (this.destBitmap == null)
        {
            throw new IllegalStateException("Can't get scaled bitmap before computing it");
        }
        return this.destBitmap;
    }

    /**
     * Compute scaled version according to destination size
     * @param destHeight the destination screen size
     * @param destWidth the destination screen size
     */
    public void computeScaled(int destHeight, int destWidth) {
        if (destHeight == 0 || destWidth == 0) {
            return;
        }

        this.destHeight = destHeight;
        this.destWidth = destWidth;

        //Calc the desired size
        int desiredScaleHeight = (int)((float)destHeight * scaleHeight);
        int desiredScaleWidth  = (int)((float)destWidth  * scaleWidth);

        //Decode only bounds of image
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(this.resources, this.resID, options);

        // Calculate inSampleSize from image bounds and decode a scaled down resource
        options.inSampleSize = calculateInSampleSize(options, desiredScaleWidth, desiredScaleHeight);
        options.inJustDecodeBounds = false;
        Bitmap tmpBitmap = BitmapFactory.decodeResource(resources, resID, options);

        //Then scale to the real exact needed size and recycle temp image
        this.destBitmap = Bitmap.createScaledBitmap(tmpBitmap, desiredScaleWidth, desiredScaleHeight, true);
        tmpBitmap.recycle();
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //Sligly modified version of
        // http://developer.android.com/training/displaying-bitmaps/load-bitmap.html#load-bitmap
        //TODO check that this code work properly on real device

        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((height / inSampleSize) > reqHeight && (width / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
