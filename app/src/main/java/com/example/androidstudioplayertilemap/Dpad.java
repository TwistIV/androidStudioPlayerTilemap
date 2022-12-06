package com.example.androidstudioplayertilemap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Dpad {
    private int centerPosX, centerPosY, buttonsRadius, buttonCenterOffset;
    private boolean isPressed;
    private Paint paint;

    int dpadLeftX, dpadRightX, dpadTopY, dpadBottomY;

    public Dpad(int centerPosX, int centerPosY, int buttonsRadius){
        this.centerPosX = centerPosX;
        this.centerPosY = centerPosY;

        this.buttonsRadius = buttonsRadius;

        this.buttonCenterOffset = 100;

        this.paint = new Paint();
        paint.setColor(Color.GRAY);

        isPressed = false;

        dpadLeftX = centerPosX - buttonCenterOffset - buttonsRadius;
        dpadRightX = centerPosX + buttonCenterOffset + buttonsRadius;
        dpadTopY = centerPosY - buttonCenterOffset - buttonsRadius;
        dpadBottomY = centerPosY + buttonCenterOffset + buttonsRadius;
    }

    public void draw(Canvas canvas) {
        //up
        canvas.drawCircle((float) centerPosX,((float) centerPosY) - buttonCenterOffset, buttonsRadius, paint);

        //down
        canvas.drawCircle((float) centerPosX,((float) centerPosY) + buttonCenterOffset, buttonsRadius, paint);

        //left
        canvas.drawCircle(((float) centerPosX) - buttonCenterOffset,(float) centerPosY, buttonsRadius, paint);

        //right
        canvas.drawCircle(((float) centerPosX) + buttonCenterOffset,(float) centerPosY, buttonsRadius, paint);
    }

    public void update() {
    }

    public boolean isPressed(double positionX, double positionY) {

        if (positionX >= dpadLeftX && positionX <= dpadRightX && positionY > dpadTopY && positionY < dpadBottomY){
            System.out.println("IS PRESSED");
            return true;
        }
        return false;
    }

    public String directionPressed(double positionX, double positionY){
        


    }

    private boolean pointIsAbove(int pointOneX, int pointOneY, int pointTwoX, int pointTwoY, int inputX, int inputY){
        if(inputY >= ((pointOneY - pointTwoY) / (pointOneX - pointTwoX)) * (inputX -  pointOneX) + centerPosX){
            return true;
        }
        return false;
    }

    public void setIsPressed(boolean isPressed){
        this.isPressed = isPressed;
    }

    public boolean getIsPressed(){
        return isPressed;
    }
}
