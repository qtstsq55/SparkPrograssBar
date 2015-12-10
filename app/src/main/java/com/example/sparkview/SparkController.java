package com.example.sparkview;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tikey on 2015/12/9.
 */
public class SparkController {
    public static final int INVALITE = 0x10;
    private float startX,startY,width,height;
    private int prograss,speed,delay = 50,maxPrograss = 100,curPrograss,borderWidth,borderHeight,createSparkNum = 8 ,sparkLift = 100;
    private float startColor,endColor,curColor;
    private List<Spark> sparks = new ArrayList<Spark>();
    public interface SparkCallBack{
        public void onStartPre();
        public void onEnd();
        public void onUpdate(float prograss);
    }
    private SparkCallBack sparkCallBack;

    public int createTopColor(float prograss){
          //进度条上部颜色
          float hue = startColor + prograss * (endColor - startColor);
          return  Color.HSVToColor(255,new float[]{hue,1f,1f});
    }

    public int createBottomColor(float prograss){
        //进度条下部颜色，仅仅透明度区别
        float hue = startColor + prograss * (endColor - startColor);
        curColor = hue;
        return  Color.HSVToColor(200, new float[]{hue, 1f, 1f});
    }

    public List<Spark> createSparks(float prograss){
        //创建4个初始粒子
        for(int i = 0; i<createSparkNum ; i++) {
            sparks.add(createSpark(prograss));
        }
        return  sparks;
    }

    public Spark createSpark(float prograss){
        //初始化粒子
        Spark spark = new Spark();
        spark.setX(getStartX() + prograss * getWidth() - rand(0f, 1f) * 10);
        spark.setY(getStartY() + getHeight());
        spark.setDepX((rand(0f, 4f) - 2) / 100);
        spark.setDepY((rand(0f, getSparkLift()) - getSparkLift() * 2) / 100);
        spark.setWeidth((int) (rand(1, 4) / 2));
        spark.setHeight((int) (rand(1, 4) / 2));
        spark.setHue(getCurColor());
        return  spark;
    }

    public float rand(float a, float b){
        double v = Math.random() * (b - a + 1) + a;
        return Math.round(v);
    }


    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getStartColor() {
        return startColor;
    }

    public void setStartColor(float startColor) {
        this.startColor = startColor;
    }

    public float getEndColor() {
        return endColor;
    }

    public void setEndColor(float endColor) {
        this.endColor = endColor;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMaxPrograss() {
        return maxPrograss;
    }

    public void setMaxPrograss(int maxPrograss) {
        this.maxPrograss = maxPrograss;
    }

    public int getPrograss() {
        return prograss;
    }

    public void setPrograss(int prograss) {
        this.prograss = prograss > maxPrograss ? maxPrograss : prograss;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getCurPrograss() {
        return curPrograss;
    }

    public void setCurPrograss(int curPrograss) {
        this.curPrograss = curPrograss > maxPrograss ? maxPrograss : curPrograss;
    }

    public float getCurColor() {
        return curColor;
    }

    public void setCurColor(float curColor) {
        this.curColor = curColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public int getBorderHeight() {
        return borderHeight;
    }

    public void setBorderHeight(int borderHeight) {
        this.borderHeight = borderHeight;
    }

    public int getSparkLift() {
        return sparkLift;
    }

    public void setSparkLift(int sparkLift) {
        this.sparkLift = sparkLift;
    }

    public int getCreateSparkNum() {
        return createSparkNum;
    }

    public void setCreateSparkNum(int createSparkNum) {
        this.createSparkNum = createSparkNum;
    }

    public List<Spark> getSparks() {
        return sparks;
    }

    public void setSparks(List<Spark> sparks) {
        this.sparks = sparks;
    }

    public SparkCallBack getSparkCallBack() {
        return sparkCallBack;
    }

    public void setSparkCallBack(SparkCallBack sparkCallBack) {
        this.sparkCallBack = sparkCallBack;
    }

}
