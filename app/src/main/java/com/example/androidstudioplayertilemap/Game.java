package com.example.androidstudioplayertilemap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private final Player player;
    private final Joystick joystick;
    private final Dpad dpad;

    Game(Context context){
        super(context);

        // Create surfaceholder object to draw to
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        // Create gameLoop object that runs updates and draws to the surface at the specified FPS
        // Takes in the current class and the surfaceHolder
        this.gameLoop = new GameLoop(this, surfaceHolder);

        //Creates player with position and size
        this.player = new Player(getContext(), 500, 500, 100);

        this.joystick = new Joystick(150, 900, 100, 75);
        this.dpad = new Dpad(300, 800, 50);

        setFocusable(true);
    }

    // Make updates to player position on a touch event
    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //player.setPosition((int) event.getX(), (int) event.getY());
                if (dpad.isPressed((double) event.getX(), (double) event.getY())){

                }

                return true;
            case MotionEvent.ACTION_MOVE:
                //player.setPosition((int) event.getX(), (int) event.getY());
                if (dpad.isPressed((double) event.getX(), (double) event.getY())){

                }
                return true;
            case MotionEvent.ACTION_UP:
                dpad.setIsPressed(false);
                return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    // Draws the fps counter on the screen by getting the average FPS
    // from the averageFPS field withing the gameLoop class
    public void drawFPS(Canvas canvas){
        //Gets averageFPS from gameLoop class
        String averageFPS = Integer.toString(gameLoop.averageFPS);

        //paint object to paint on screen
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(40);

        // Draw the text to the canvas
        canvas.drawText("FPS: " + averageFPS, 100,40, paint);
    }

    // draw to canvas every frame
    //this draw method is called from the gameLoop class at the specified FPS
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawFPS(canvas);
        player.draw(canvas);
        //joystick.draw(canvas);
        dpad.draw(canvas);
    }

    // update class info every frame
    // this update method is called from the gameLoop class at the specified FPS
    public void update() {
        //joystick.update();
        dpad.update();
        player.update();
    }
}
