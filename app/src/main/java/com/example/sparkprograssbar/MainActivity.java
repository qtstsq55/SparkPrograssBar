package com.example.sparkprograssbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sparkview.SparkController;
import com.example.sparkview.SparkPrograssBar;

public class MainActivity extends AppCompatActivity {

    private SparkPrograssBar sparkPrograssbar;
    private SparkController sparkController;
    private Button btn_start,btn_end,btn_reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvents();
        initSparkPrograssBar();
    }


    private void initView(){
        btn_end = (Button) findViewById(R.id.btn_end);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        sparkPrograssbar = (SparkPrograssBar) findViewById(R.id.sparkPrograssbar);
    }

    private void initEvents(){
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPrograssBar(100);
            }
        });

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPrograssBar();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPrograssBar();
            }
        });
    }


    private void initSparkPrograssBar(){
        sparkController = new SparkController();
        sparkController.setStartX(50);//组件左上角X坐标
        sparkController.setStartY(45);//组件右上角坐标
        sparkController.setStartColor(0f);//进度条初始颜色(红色)，HSV颜色模式
        sparkController.setEndColor(120f);//进度条结束颜色(绿色)，HSV颜色模式
        sparkController.setCurColor(sparkController.getStartColor());//进度条当前颜色,可有可无
        sparkController.setWidth(620);//进度条宽度
        sparkController.setHeight(10);//进度条高度
        sparkController.setSpeed(1);//进度步长
        sparkController.setDelay(30);//刷新频率
        sparkController.setSparkCallBack(new SparkController.SparkCallBack() {
            @Override
            public void onStartPre() {
                //开始前回调
            }

            @Override
            public void onEnd() {
                //结束后回调
            }

            @Override
            public void onUpdate(float prograss) {
                //更新回调
            }
        });
        sparkPrograssbar.setSparkController(sparkController);
    }

    private void startPrograssBar(int prograss){
        sparkPrograssbar.slidSprk(prograss);
    }

    private void stopPrograssBar(){
        sparkPrograssbar.stopSlidSpak();
    }

    private void resetPrograssBar(){
        sparkController.getSparks().clear();
        sparkController.setCurPrograss(0);
        startPrograssBar(100);
    }

}
