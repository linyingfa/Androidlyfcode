package com.example.lyf.androidlyfcode.view.line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangweizhu on 2018/1/8.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class CusLine extends View {

    List<String> date = new ArrayList<>();
    Paint paint;

    public CusLine(Context context) {
        super(context);
        init();
    }

    public CusLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CusLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setTextSize(18);

        date.add("2017-08-17");
        date.add("2017-08-16");
        date.add("2017-08-15");
        date.add("2017-08-14");
        date.add("2017-08-13");
        date.add("2017-08-12");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        int width = getWidth();
        //X,Y这个坐标并不是文字的左上角，而是一个与左下角比较接近的位置。
        canvas.drawText(date.get(0), getWidth() / 2, 40, paint);
        float startx = getWidth() / 2;
        float height = getHeight() / 2;
        float endy = getHeight();
        float textWidth = paint.measureText(date.get(0));//文字的一半作+文字开始绘制点，作为线的起点
        canvas.drawLine(startx + textWidth / 2, 40, startx + textWidth / 2, endy, paint);


    }
}
