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

    Game(Context context){
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.gameLoop = new GameLoop(this, surfaceHolder);

        this.player = new Player(getContext(), 500, 500, 100);

        this.joystick = new Joystick(150, 900, 100, 75);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                player.setPosition((int) event.getX(), (int) event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                player.setPosition((int) event.getX(), (int) event.getY());
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

    public void drawFPS(Canvas canvas){
        String averageFPS = Integer.toString(gameLoop.averageFPS);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(40);
        canvas.drawText("FPS: " + averageFPS, 100,40, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawFPS(canvas);
        player.draw(canvas);
        joystick.draw(canvas);
    }

    public void update() {
        joystick.update();
        player.update();
    }
}
