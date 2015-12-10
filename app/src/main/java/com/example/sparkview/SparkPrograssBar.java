package com.example.sparkview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tikey on 2015/12/9.
 */
public class SparkPrograssBar extends View {

    private SparkController sparkController;
    private Paint paint;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if(msg.what == SparkController.INVALITE) {
               sparkController.setCurPrograss(sparkController.getCurPrograss() + sparkController.getSpeed());
               postInvalidate();
               if (!sparkController.getSparks().isEmpty()) {
                   //30ms刷新一次
                   sendEmptyMessageDelayed(SparkController.INVALITE, sparkController.getDelay());
               }
           }
        }
    };

    public SparkPrograssBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(sparkController == null) throw new IllegalStateException("SparkController can not null");
        sparkController.setBorderWidth(getMeasuredWidth());
        sparkController.setBorderHeight(getMeasuredHeight());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(sparkController == null) throw new IllegalStateException("SparkController can not null");
        paint.setStyle(Paint.Style.FILL);
        float prograss = ((float) sparkController.getCurPrograss() / sparkController.getPrograss());
        float currentX = sparkController.getStartX() + (prograss * sparkController.getWidth());
        float currentY = sparkController.getStartY() + sparkController.getHeight();
        paint.setColor(sparkController.createTopColor(prograss));
        //进度条上半部分
        canvas.drawRect(sparkController.getStartX(), sparkController.getStartY(), currentX, currentY - sparkController.getHeight() / 2, paint);
        paint.setColor(sparkController.createBottomColor(prograss));
        //进度条下半部分
        canvas.drawRect(sparkController.getStartX(), currentY - sparkController.getHeight() / 2, currentX, currentY, paint);
        if(sparkController.getCurPrograss() < sparkController.getPrograss()) {
            //每次增加4个粒子
            sparkController.createSparks(prograss);
        }
        //绘制所有粒子
        for(int i = 0 ; i<sparkController.getSparks().size() ;i++){
            Spark spark = sparkController.getSparks().get(i);
            spark.setDepX(spark.getDepX() + (sparkController.rand(0f, 6f) - 3) / 100);
            spark.setDepY(spark.getDepY() + 0.2f);
            float sparkX = spark.getX() + spark.getDepX() / 2 + spark.getWeidth();
            float sparkY = spark.getY() + 6 * spark.getDepY();
            spark.setX(sparkX > currentX ? currentX - spark.getWeidth():sparkX);
            spark.setY(sparkY);
            if(spark.getY() > sparkController.getBorderHeight()){
                sparkController.getSparks().remove(i);
            }else{
                paint.setColor(spark.createColor(sparkController));
                canvas.drawRect(spark.getX(),spark.getY(),spark.getX() + spark.getWeidth(),spark.getY() + spark.getHeight(),paint);
            }
        }
        if(sparkController.getSparks().isEmpty() && sparkController.getSparkCallBack() != null){
             sparkController.getSparkCallBack().onEnd();
        }
        if(sparkController.getSparkCallBack() != null){
            sparkController.getSparkCallBack().onUpdate(prograss);
        }
    }


    public SparkController getSparkController() {
        return sparkController;
    }

    public void setSparkController(SparkController sparkController) {
        this.sparkController = sparkController;
    }

    public void slidSprk(int prograss) {
        if(sparkController.getSparkCallBack() != null){
            sparkController.getSparkCallBack().onStartPre();
        }
        sparkController.setPrograss(prograss);
        invalidate();
        mHandler.removeMessages(SparkController.INVALITE);
        mHandler.sendEmptyMessage(SparkController.INVALITE);
    }

    public void stopSlidSpak(){
        mHandler.removeMessages(SparkController.INVALITE);
        if(sparkController.getSparkCallBack() != null){
            sparkController.getSparkCallBack().onEnd();
        }
    }
}
