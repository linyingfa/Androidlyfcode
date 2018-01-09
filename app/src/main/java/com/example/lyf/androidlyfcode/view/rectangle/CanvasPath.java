package com.example.lyf.androidlyfcode.view.rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangweizhu on 2018/1/9.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class CanvasPath extends View {


    Paint mPaint;


    public CanvasPath(Context context) {
        super(context);
    }

    public CanvasPath(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasPath(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


//     *TODO Path作用
//     * 本次特地开了一篇详细讲解Path，为什么要单独摘出来呢，这是因为Path在2D绘图中是一个很重要的东西。
//     * 在前面我们讲解的所有绘制都是简单图形(如 矩形 圆 圆弧等)，
//     * 而对于那些复杂一点的图形则没法去绘制(如绘制一个心形 正多边形 五角星等)，
//     * 而使用Path不仅能够绘制简单图形，也可以绘制这些比较复杂的图形。
//     * 另外，根据路径绘制文本和剪裁画布都会用到Path。
//       Path封装了由直线和曲线(二次，三次贝塞尔曲线)构成的几何路径。
//       你能用Canvas中的drawPath来把这条路径画出来(同样支持Paint的不同绘制模式)，
//       也可以用于剪裁画布和根据路径绘制文字。
//       我们有时会用Path来描述一个图像的轮廓，所以也会称为轮廓线(轮廓线仅是Path的一种使用方法，两者并不等价)

    //TODO 路径有开放和封闭的区别:
    //              封闭路径	首尾相接形成了一个封闭区域
    //              开放路径	没有首位相接形成封闭区域
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();             // 创建画笔
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);              // 边框宽

        draw1(canvas);
    }


    void draw1(Canvas canvas) {

        // 移动坐标系到屏幕中心(宽高数据在onSizeChanged中获取)
        canvas.translate(getWidth() / 2, getHeight() / 2);

        Path path = new Path();                     // 创建Path

        /**
         第一次由于之前没有过操作，所以默认点就是坐标原点O，结果就是坐标原点O到A(200,200)之间连直线(用蓝色圈1标注)。
         第二次lineTo的时候，由于上次的结束位置是A(200,200),所以就是A(200,200)到B(200,0)之间的连线(用蓝色圈2标注)。
         */
        path.lineTo(200, 200);                      // lineTo
        path.lineTo(200, 0);

        canvas.drawPath(path, mPaint);              // 绘制Path
    }


    void draw2(Canvas canvas) {
        /**
         * moveTo只改变下次操作的起点，在执行完第一次LineTo的时候，本来的默认点位置是A(200,200),
         * 但是moveTo将其改变成为了C(200,100),所以在第二次调用lineTo的时候就是连接C(200,100) 到 B(200,0)
         * 之间的直线(用蓝色圈2标注)。
         */

        canvas.translate(getWidth() / 2, getHeight() / 2);  // 移动坐标系到屏幕中心

        Path path = new Path();                     // 创建Path

        path.lineTo(200, 200);                      // lineTo

        path.moveTo(200, 100);                       // moveTo

        path.lineTo(200, 0);                         // lineTo

        canvas.drawPath(path, mPaint);              // 绘制Path
    }

    void draw3(Canvas canvas) {

        /**
         * setLastPoint是重置上一次操作的最后一个点，在执行完第一次的lineTo的时候，最后一个点是A(200,200),
         * 而setLastPoint更改最后一个点为C(200,100),所以在实际执行的时候，
         * 第一次的lineTo就不是从原点O到A(200,200)的连线了，而变成了从原点O到C(200,100)之间的连线了。
         在执行完第一次lineTo和setLastPoint后，最后一个点的位置是C(200,100),
         所以在第二次调用lineTo的时候就是C(200,100) 到 B(200,0) 之间的连线(用蓝色圈2标注)。
         */

        canvas.translate(getWidth() / 2, getHeight() / 2);  // 移动坐标系到屏幕中心

        Path path = new Path();                     // 创建Path

        path.lineTo(200, 200);                      // lineTo

        path.setLastPoint(200, 100);                 // setLastPoint

        path.lineTo(200, 0);                         // lineTo

        canvas.drawPath(path, mPaint);              // 绘制Path
    }

    void draw4(Canvas canvas) {
        /**
         * close方法用于连接当前[最后]一个点和[最初]的一个点(如果两个点不重合的话)，最终形成一个封闭的图形。
         */
        //TODO 注意：close的作用是封闭路径，与连接当前最后一个点和第一个点并不等价。
        //TODO 如果连接了最后一个点和第一个点仍然无法形成封闭图形，则close什么 也不做。

        canvas.translate(getWidth() / 2, getHeight() / 2);  // 移动坐标系到屏幕中心

        Path path = new Path();                     // 创建Path

        path.lineTo(200, 200);                      // lineTo

        path.lineTo(200, 0);                         // lineTo

        path.close();                               // close

        canvas.drawPath(path, mPaint);              // 绘制Path
    }
}
