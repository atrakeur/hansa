package fr.univ_rouen.hansa.view.utils;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import fr.univ_rouen.hansa.view.GameBoardView;

public class DrawingThread extends Thread {

    private volatile boolean isRunning;

    private GameBoardView view;
    private SurfaceHolder holder;

    public DrawingThread(GameBoardView view, SurfaceHolder holder) {
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
                c = holder.lockCanvas(null);
                synchronized (holder) {
                    if (view != null && c != null) {
                        view.redraw(c);
                    }
                }
                //Wait
                sleep(100);
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
