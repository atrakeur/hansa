package fr.univ_rouen.hansa.view.utils;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingThread extends Thread {

    private volatile boolean isRunning;

    private SurfaceView view;
    private SurfaceHolder holder;

    public DrawingThread(SurfaceView view, SurfaceHolder holder) {
        this.view = view;
        this.holder = holder;

        final DrawingThread threadInstance = this;
        view.getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceCreated(SurfaceHolder holder) {
                threadInstance.setRunning(true);
                threadInstance.start();
            }
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                threadInstance.setRunning(false);
                while (retry) {
                    try {
                        threadInstance.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }

    public void setRunning(boolean run) {
        this.isRunning = run;
    }

    public void run() {
        while(isRunning) {
            Canvas c = null;
            try {
                //Lock canvas and draw view in backbuffer
                synchronized (holder) {
                    c = holder.lockCanvas(null);
                    if (view != null && c != null) {
                        view.draw(c);
                    }
                }
                //Wait
                sleep(500);
            } catch(InterruptedException e) {
                e.printStackTrace();
            } finally {
                //Unlock and draw canvas on top buffer
                if (c != null) {
                    holder.unlockCanvasAndPost(c);
                }
            }
        }
    }

}
