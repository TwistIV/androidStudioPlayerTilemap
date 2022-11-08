package com.example.androidstudioplayertilemap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private Paint paint;

    public int x, y;
    public int size;
    public int speed;
    public String facing;

    Player(Context context, int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect((float) x,(float) y, size+x, size+y, paint);
    }

    public void update() {
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
