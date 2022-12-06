package com.example.androidstudioplayertilemap;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{
    private boolean isRunning = false;
    private Game game;
    private SurfaceHolder surfaceHolder;

    // target and average FPS
    final int FPS = 60;
    int averageFPS = 0;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        this.game = game;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        double frameInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // timer and frameCount for FPS calculation
        long timer = 0;
        int frameCount = 0;

        Canvas canvas;
        while(isRunning){

            try {
                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / frameInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                // runs gameloop if frame interval has passed
                if (delta >= 1) {
                    canvas = surfaceHolder.lockCanvas();
                    game.update();
                    game.draw(canvas);

                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (IllegalArgumentException e){
                        e.printStackTrace();
                    }

                    delta--;
                    frameCount++;
                }

                // updates averageFPS field every second
                if(timer >= 1000000000){
                    averageFPS = frameCount;
                    frameCount = 0;
                    timer = 0;
                }

            } catch(IllegalArgumentException e){
                e.printStackTrace();
            }

        }
    }
}
