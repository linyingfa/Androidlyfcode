package com.example.lyf.androidlyfcode.view.rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangweizhu on 2018/1/9.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class CanvasOperation extends View {
    public CanvasOperation(Context context) {
        super(context);
    }

    public CanvasOperation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasOperation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * save	保存当前画布状态  入栈
     * restore	回滚到上一次保存的状态  出栈
     * translate	相对于当前位置位移
     * rotate	旋转
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        translate(canvas);
//        scsale(canvas);
//        rotate(canvas);
        skew(canvas);
    }

    /**
     * ⑴位移(translate)
     */
    public void translate(Canvas canvas) {
        // 在坐标原点绘制一个黑色圆形
        canvas.save();//保存当前的状态
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
        canvas.restore();//恢复最初状态
        // 在坐标原点绘制一个蓝色圆形
        mPaint.setColor(Color.BLUE);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
    }


    /**
     * ⑵缩放(scale)
     */
    public void scsale(Canvas canvas) {
        boolean flga = false;
        if (flga) {
//        public void scale (float sx, float sy)
//        public final void scale (float sx, float sy, float px, float py)
//        两个方法中前两个参数是相同的分别为x轴和y轴的缩放比例。而第二种方法比前一种多了两个参数，用来控制缩放中心位置的。
            //TODO
 /*       取值范围(n)	说明:
                [-∞, -1)	先根据缩放中心放大n倍，再根据中心轴进行翻转
                -1	        根据缩放中心轴进行翻转
                (-1, 0)	    先根据缩放中心缩小到n，再根据中心轴进行翻转
                0	        不会显示，若sx为0，则宽度为0，不会显示，sy同理
                (0, 1)	    根据缩放中心缩小到n
                1	        没有变化
                (1, +∞)	根据缩放中心放大n倍*/

            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            // 将坐标系原点移动到画布正中心
//        canvas.save();
            canvas.translate(getWidth() / 2, getHeight() / 2);
            //TODO android 中 X右是正方向，Y下方向是正方向
            RectF rect = new RectF(0, -400, 400, 0);   // 矩形区域
            Paint mPaint = new Paint();
            mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
            canvas.drawRect(rect, mPaint);
            canvas.drawPoint(rect.centerX(), rect.centerY(), paint);

//        canvas.scale(0.5f,0.5f);
//        canvas.scale(0.5f,0.1f);
//        调用两次缩放则 x轴实际缩放为0.5x0.5=0.25 y轴实际缩放为0.5x0.1=0.05
            //TODO:和位移(translate)一样，缩放也是可以叠加的。
//        canvas.scale(0.5f, 0.5f);                  //1.画布缩放
//        canvas.scale(0.5f, 0.5f, 200, 0);          //2.画布缩放  <-- 缩放中心向右偏移了200个单位
//        canvas.scale(-0.5f, -0.5f);                //3.画布缩放，当缩放比例为负数的时候会根据缩放中心轴进行翻转
            canvas.scale(-0.5f, -0.5f, 300, 0);          //4.画布缩放  <-- 缩放中心向右偏移了300个单位

            mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
            canvas.drawRect(rect, mPaint);

            canvas.restore();
            canvas.drawPoint(getWidth() / 2, getHeight() / 2, paint);
            canvas.drawLine(getWidth() / 2, getHeight() / 2, 0, 300, paint);

        } else {
            // 将坐标系原点移动到画布正中心
            canvas.translate(getWidth() / 2, getHeight() / 2);
            RectF rect = new RectF(-400, -400, 400, 400);   // 矩形区域
            Paint mPaint = new Paint();
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(10);
            mPaint.setStyle(Paint.Style.STROKE);
            for (int i = 0; i <= 20; i++) {
                canvas.scale(0.9f, 0.9f);
                canvas.drawRect(rect, mPaint);
            }

        }
    }


    /**
     * ⑶旋转(rotate)
     */

    public void rotate(Canvas canvas) {
        // 将坐标系原点移动到画布正中心
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(getWidth() / 2, getHeight() / 2);

        canvas.drawCircle(0, 0, 400, mPaint);          // 绘制两个圆形
        canvas.drawCircle(0, 0, 380, mPaint);

        for (int i = 0; i <= 360; i += 10) {               // 绘制圆形之间的连接线
            canvas.drawLine(0, 380, 0, 400, mPaint);
            canvas.rotate(10);
        }

/*        RectF rect = new RectF(0, -400, 400, 0);   // 矩形区域

        canvas.drawRect(rect, mPaint);

//        canvas.rotate(180);
//        canvas.rotate(20);
//        调用两次旋转，则实际的旋转角度为180+20=200度。
        //TODO 旋转也是可叠加的
//        canvas.rotate(180);                     // 旋转180度 <-- 默认旋转中心为原点
        canvas.rotate(180, 200, 0);               // 旋转180度 <-- 旋转中心向右偏移200个单位

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect, mPaint);*/
    }


    /**
     * ⑷错切(skew)
     */

    public void skew(Canvas canvas) {
        /**
         * skew这里翻译为错切，错切是特殊类型的线性变换。
         错切只提供了一种方法：
         public void skew (float sx, float sy)
         参数含义：
         //TODO   float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
         //TODO  float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
         变换后:
         //TODO X = x + sx * y
         //TODO Y = sy * x + y
         */

        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);

        // 将坐标系原点移动到画布正中心
        canvas.translate(getWidth() / 2, getHeight() / 2);

        RectF rect = new RectF(0, 0, 200, 200);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect, mPaint);

        //tan45=1,tan0 =0
        canvas.skew(1, 0);                       // 水平错切 <- 45度,tan0是在Y轴的圆点出切
        canvas.skew(0, 1);                       // 垂直错切

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect, mPaint);


    }
}
