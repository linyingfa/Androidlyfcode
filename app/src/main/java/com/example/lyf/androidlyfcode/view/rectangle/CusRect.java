package com.example.lyf.androidlyfcode.view.rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangweizhu on 2018/1/8.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class CusRect extends View {

    private Paint mPaint;

    public CusRect(Context context) {
        super(context);
        initView();
    }

    public CusRect(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CusRect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    void initView() {
        mPaint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 圆角矩形
/*        RectF rectF = new RectF(100, 100, 800, 400);

        // 绘制背景矩形
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(rectF, mPaint);

        // 绘制圆角矩形
        mPaint.setColor(Color.BLUE);
        //圆角矩形的角实际上不是一个正圆的圆弧，而是椭圆的圆弧，这里的两个参数实际上是椭圆的两个半径
        //RX RY是半径，，当你让 rx大于350(宽度的一半)， ry大于150(高度的一半) 时奇迹就出现了， 你会发现圆角矩形变成了一个椭圆
        canvas.drawRoundRect(rectF, 400, 200, mPaint);
        //
        mPaint.setColor(Color.RED);
        canvas.drawLine(100, 100, 700, 400, mPaint);*/


        // 绘制椭圆：绘制椭圆实际上就是绘制一个矩形的内切图形，
        //          如果你传递进来的是一个长宽相等的矩形(即正方形)，那么绘制出来的实际上就是一个圆。例如：100,100,800,800
   /*     // 第一种
        RectF rectFOval = new RectF(100,100,800,400);
        canvas.drawOval(rectFOval,mPaint);
        // 第二种
        canvas.drawOval(100,100,800,400,mPaint);*/


        //绘制圆：
//        canvas.drawCircle(500, 500, 400, mPaint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆。

        //绘制圆弧：

        RectF rectF = new RectF(100, 100, 800, 400);
        // 绘制背景矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF, mPaint);

        // 绘制圆弧
        /**
         *TODO
         startAngle  // 开始角度
         sweepAngle  // 扫过角度
         useCenter   // 是否使用中心
         */

        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF, 0, 90, false, mPaint);

        //----------使用了中心点之后绘制出来类似于一个扇形，而不使用中心点则是圆弧起始点和结束点之间的连线加上圆弧围成的图形--------

        RectF rectF2 = new RectF(100,600,800,900);
        // 绘制背景矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF2,mPaint);
        // 绘制圆弧
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF2,0,180,true,mPaint);
    }
}
