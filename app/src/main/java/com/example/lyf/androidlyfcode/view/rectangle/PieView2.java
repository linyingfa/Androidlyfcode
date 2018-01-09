package com.example.lyf.androidlyfcode.view.rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.lyf.androidlyfcode.utils.WindowUtils;

import java.util.ArrayList;

/**
 * Created by huangweizhu on 2018/1/9.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class PieView2 extends View {

    private Paint mPaint;
    private Context mContext;
    private int mWidth, mHeight;
    private RectF rectF;
    private float mStartAngle = 0;
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000};
    ArrayList<PieData> datas = new ArrayList<>();

    public PieView2(Context context) {
        super(context);
        initViewConfig(context);
    }

    public PieView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewConfig(context);
    }

    public PieView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewConfig(context);
    }


    private void initViewConfig(Context mContext) {
        this.mContext = mContext;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);


        PieData pieData = new PieData("sloop", 60);
        PieData pieData2 = new PieData("sloop", 30);
        PieData pieData3 = new PieData("sloop", 40);
        PieData pieData4 = new PieData("sloop", 20);
        PieData pieData5 = new PieData("sloop", 20);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
    }


    /**
     * @param w    布局文件的是match_parent，手机的屏幕是1080px
     * @param h    布局文件是200dp,对应的是550px
     * @param oldw
     * @param oldh des : 当此View的宽度，高度发生改变的时候就会调用这个方法
     *             一般绘制的view宽高是固定的 这个应该在自定义viewgroup常用
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;//1080
        mHeight = h;//550
        int dpmHeight = WindowUtils.px2dp(mContext, mHeight);
        Log.i("dpmHeight", "dpmHeight=" + dpmHeight);//dpmHeight=200
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);  // 饼状图半径
        rectF = new RectF(-r, -r, r, r);                     // 饼状图绘制区域  // 绘制背景矩形

    }

    int curroangle = 0;

    /**
     * save	保存当前画布状态
     * restore	回滚到上一次保存的状态
     * translate	相对于当前位置位移
     * rotate	旋转
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);  // 饼状图半径
        RectF rect = new RectF(-r, -r, r, r);                     // 饼状图绘制区域
        for (int i = 0; i < datas.size(); i++) {
            PieData pie = datas.get(i);
            mPaint.setColor(mColors[i]);
            canvas.drawArc(rect, curroangle, angle[i + 1], true, mPaint);
            curroangle = angle[i + 1];
        }
    }

    int[] angle = {30, 60, 90, 120, 150, 170};
}
