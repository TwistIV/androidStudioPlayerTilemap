package com.example.androidstudioplayertilemap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {
    private int joystickBaseRadius;
    private int joystickRadius;
    private int joystickBaseX, joystickBaseY, joystickX, joystickY;

    private Paint joystickBasePaint;
    private Paint joystickPaint;

    Joystick(int x, int y, int baseRadius, int joystickRadius){

        this.joystickBaseX = x;
        this.joystickBaseY = y;
        this.joystickX = x;
        this.joystickY = y;

        // radius of joystick base and handle
        this.joystickBaseRadius = baseRadius;
        this.joystickRadius = joystickRadius;

        joystickBasePaint = new Paint();
        joystickBasePaint.setColor(Color.GRAY);
        joystickBasePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        this.joystickPaint = new Paint();
        joystickPaint.setColor(Color.GRAY);
        joystickPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    public void draw(Canvas canvas) {
        canvas.drawCircle(joystickBaseX, joystickBaseY, joystickBaseRadius, joystickBasePaint);
        canvas.drawCircle(joystickX, joystickY, joystickRadius, joystickPaint);
    }

    public void update() {
    }
}
