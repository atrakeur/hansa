package fr.univ_rouen.hansa.view.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public class ResourceRepository {

    private int destWidth;
    private int destHeigth;
    private float scaleWidth;
    private float scaleHeigth;

    private Resources resourcesLoader;
    private String referenceResource = "background";
    private HashMap<String, ScaledImage> resources;

    /**
     * Create a new repository that old image and scaled versions
     */
    public ResourceRepository(Resources resources) {
        this.resourcesLoader = resources;
        this.resources = Maps.newHashMap();
    }

    /**
     * Create a new repository linked to a given view
     * @param view the view to link and adapt to
     */
    public ResourceRepository(final SurfaceView view, Resources resources) {
        this(resources);

        final ResourceRepository instance = this;

        view.getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceCreated(SurfaceHolder holder) {
                instance.computeResources(view.getWidth(), view.getHeight());
            }
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
            public void surfaceDestroyed(SurfaceHolder holder) {}
        });
    }

    /**
     * Load a given resource into live memory
     * @param key the key to associate
     * @param id the id to load
     */
    public void addResource(String key, int id, float sizeWidth, float sizeHeight) {
        ScaledImage scaledImage = new ScaledImage(this.resourcesLoader, id, sizeWidth, sizeHeight);
        resources.put(key, scaledImage);
        scaledImage.computeScaled(destHeigth, destWidth);
    }

    public boolean hasResource(String key) {
        return resources.containsKey(key);
    }

    /**
     * Remove a resource from live memory
     * @param key the key to unload
     */
    public void removeResource(String key) {
        resources.remove(key);
    }

    /**
     * Compute scaled resources according to given resolution from reference resource (ie the background)
     * @param width screen width
     * @param height screen height
     * @throws java.lang.IllegalStateException if no referenceResource can be found
     */
    public void computeResources(int width, int height) {
        if (!resources.containsKey(referenceResource)) {
            throw new IllegalStateException("Can't compute scaled resource without reference resource");
        }

        //Calculate scale factor according to total size
        destWidth = width;
        destHeigth = height;

        for (Map.Entry<String, ScaledImage> entry : resources.entrySet()) {
            entry.getValue().computeScaled(destHeigth, destWidth);
        }
    }

    /**
     * Get a ready to display scaled resource
     * @param key the key of the resource
     */
    public Bitmap getScaledResource(String key) {
        if (!resources.containsKey(key)) {
            throw new IllegalArgumentException("Can't get undeclared resource "+key);
        }

        return resources.get(key).getScaledBitmap();
    }

    public int getPercentToScreenWidth(float percent) {
        return (int)(destWidth * percent);
    }

    public int getPercentToScreenHeight(float percent) {
        return (int)(destHeigth * percent);
    }

    public float getScreenWidthToPercent(float width) {
        return width / destWidth;
    }

    public float getScreenHeigthToPercent(float heigth) {
        return heigth / destHeigth;
    }

    public void clear() {
        resources.clear();
    }

}
