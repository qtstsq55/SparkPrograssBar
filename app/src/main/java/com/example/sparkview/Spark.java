package com.example.sparkview;

import android.graphics.Color;

/**
 * Created by Tikey on 2015/12/9.
 */
public class Spark {
    private int weidth,height;
    private float hue,x,y,depX,depY;

    public int createColor(SparkController sparkController){
        return  Color.HSVToColor(new float[]{hue, 1f,1f});
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getDepX() {
        return depX;
    }

    public void setDepX(float depX) {
        this.depX = depX;
    }

    public float getDepY() {
        return depY;
    }

    public void setDepY(float depY) {
        this.depY = depY;
    }

    public int getWeidth() {
        return weidth;
    }

    public void setWeidth(int weidth) {
        this.weidth = weidth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getHue() {
        return hue;
    }

    public void setHue(float hue) {
        this.hue = hue;
    }

}
