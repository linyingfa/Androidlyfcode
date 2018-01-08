package com.example.lyf.androidlyfcode.view.point;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangweizhu on 2018/1/8.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class CusPoint extends View {


    public CusPoint(Context context) {
        super(context);
        initVieconfig();
    }

    public CusPoint(Context context, AttributeSet attrs) {
        super(context, attrs);
        initVieconfig();
    }

    public CusPoint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initVieconfig();
    }

    private Paint mPaint = null;

    void initVieconfig() {
        // 1.创建一个画笔
        mPaint = new Paint();
        // 2.初始化画笔
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(200, 200, mPaint);     //在坐标(200,200)位置绘制一个点
        canvas.drawPoints(new float[]{          //绘制一组点，坐标位置由float数组指定
                500, 500,
                500, 600,
                500, 700
        }, mPaint);
    }
}
