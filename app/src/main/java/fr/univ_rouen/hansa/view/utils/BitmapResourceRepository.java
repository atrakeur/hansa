package fr.univ_rouen.hansa.view.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public class BitmapResourceRepository {

    private int destWidth;
    private int destHeigth;
    private float scale;

    private String referenceResource = "background";
    private HashMap<String, Bitmap> resources;
    private HashMap<String, Bitmap> scaledResources;

    /**
     * Create a new repository that old image and scaled versions
     */
    public BitmapResourceRepository() {
        resources = Maps.newHashMap();
        scaledResources = Maps.newHashMap();
    }

    /**
     * Create a new repository linked to a given view
     * @param view the view to link and adapt to
     */
    public BitmapResourceRepository(final SurfaceView view) {
        this();

        final BitmapResourceRepository instance = this;

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
     * @param res the resource holder
     * @param id the id to load
     */
    public void addResource(String key, Resources res, int id) {
        if (resources.containsKey(key)) {
            throw new IllegalArgumentException("Key allready loaded");
        }

        resources.put(key, BitmapFactory.decodeResource(res, id));
        scaledResources.remove(key);
    }

    /**
     * Remove a resource from live memory
     * @param key the key to unload
     */
    public void removeResource(String key) {
        resources.remove(key);
        scaledResources.remove(key);
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

        //get reference
        Bitmap reference = resources.get(referenceResource);

        //Calculate scale factor according to reference
        destWidth = width;
        destHeigth = height;
        scale = (float) reference.getHeight() / (float) height;

        for (Map.Entry<String, Bitmap> entry : resources.entrySet()) {
            //calculate resource size
            int newWidth = getScaledWidth(entry.getValue().getWidth());
            int newHeight = getScaledHeight(entry.getValue().getHeight());

            //remove old scaledResource and create new
            scaledResources.remove(entry.getKey());
            scaledResources.put(entry.getKey(), Bitmap.createScaledBitmap(entry.getValue(), newWidth, newHeight, true));
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

        if (!scaledResources.containsKey(key)) {
            throw new IllegalArgumentException("Scaled resource not computed");
        }

        return scaledResources.get(key);
    }

    public int getScaledWidth(int width) {
        return Math.round(width / scale);
    }

    public int getScaledHeight(int height) {
        return Math.round(height / scale);
    }

}
