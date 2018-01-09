package com.example.lyf.androidlyfcode.view.rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangweizhu on 2018/1/9.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class CanvasPictureText extends View {


    Paint textPaint;

    public CanvasPictureText(Context context) {
        super(context);
    }

    public CanvasPictureText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasPictureText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        textPaint = new Paint();          // 创建画笔
        textPaint.setColor(Color.BLACK);        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(50);              // 设置字体大小

        drawText1(canvas);

    }


    void drawText1(Canvas canvas) {
        /**
         * //  第一类只能指定文本基线位置。
         public void drawText (String text, float x, float y, Paint paint)
         public void drawText (String text, int start, int end, float x, float y, Paint paint)
         public void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
         public void drawText (char[] text, int index, int count, float x, float y, Paint paint)
         */

        //TODO 基线x默认在字符串左侧，基线y默认在字符串下方

        // 文本(要绘制的内容)
        String str = "ABCDEFG";
        // 参数分别为 (文本 基线x 基线y 画笔)
        canvas.drawText(str, 200, 500, textPaint);


        // 文本(要绘制的内容)
        // 参数分别为 (字符串 开始截取位置 结束截取位置 基线x 基线y 画笔)
        canvas.drawText(str, 1, 3, 200, 500, textPaint);

        // 字符数组(要绘制的内容)
        char[] chars = "ABCDEFG".toCharArray();
        // 参数为 (字符数组 起始坐标 截取长度 基线x 基线y 画笔)
        canvas.drawText(chars, 1, 3, 200, 500, textPaint);
    }

    void drawText2(Canvas canvas) {
        /**
         // 第二类可以分别指定每个文字的位置。
         public void drawPosText (String text, float[] pos, Paint paint)
         public void drawPosText (char[] text, int index, int count, float[] pos, Paint paint)
         */

//       反对理由:
//        1	必须指定所有字符位置，否则直接crash掉，反人类设计
//        2	性能不佳，在大量使用的时候可能导致卡顿
//        3	不支持emoji等特殊字符，不支持字形组合与分解
        String str = "SLOOP";
        canvas.drawPosText(str, new float[]{
                100, 100,    // 第一个字符位置
                200, 200,    // 第二个字符位置
                300, 300,    // ...
                400, 400,
                500, 500
        }, textPaint);
    }

    void drawText3(Canvas canvas) {
        /**
         // 第三类是指定一个路径，根据路径绘制文字。
         public void drawTextOnPath (String text, Path path, float hOffset, float vOffset, Paint paint)
         public void drawTextOnPath (char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)
         */
    }
}
