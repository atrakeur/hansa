package fr.univ_rouen.hansa.view.utils;

import android.graphics.Bitmap;

public class ScaledImage {

    private float scaleWidth;
    private float scaleHeight;
    private Bitmap sourceBitmap;

    private int destWidth;
    private int destHeight;
    private Bitmap destBitmap;

    /**
     * Create a new ScaledImage according to a bitmap and a relative size to screen
     * @param image the source bitmap
     * @param scaleWidth the percentage taken on screen
     * @param scaleHeigth the percentage taken on screen
     */
    public ScaledImage(Bitmap image, float scaleWidth, float scaleHeigth) {
        this.sourceBitmap = image;
        this.scaleWidth = scaleWidth;
        this.scaleHeight = scaleHeigth;
    }

    public Bitmap getSourceBitmap() {
        return this.sourceBitmap;
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
        this.destHeight = destHeight;
        this.destWidth = destWidth;

        //Calc the desired size
        int desiredScaleHeight = (int)((float)destHeight * scaleHeight);
        int desiredScaleWidth  = (int)((float)destWidth  * scaleWidth);

        //Rescale the image
        this.destBitmap = Bitmap.createScaledBitmap(sourceBitmap, desiredScaleWidth, desiredScaleHeight, true);
    }

}
