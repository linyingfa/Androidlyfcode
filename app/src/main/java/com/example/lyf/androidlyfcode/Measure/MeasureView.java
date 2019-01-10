package com.example.lyf.androidlyfcode.Measure;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2019/1/10.
 */

public class MeasureView extends View {
    public MeasureView(Context context) {
        super(context);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //1. 覆写onMeasure
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 3. 调用setMeasuredDimension，指定视图在屏幕上的大小、
        int measureWidth = measureWidth(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeight = measureHeight(heightMeasureSpec);


      /*   <com.example.lyf.androidlyfcode.Measure.MeasureView
        android:id="@+id/measureview"
        android:layout_width="match_parent"
        android:layout_height="100dp" />*/

        /* widthMeasureSpec = 1073742904 , measureWidth = 1080 , modeWidth = 1073741824 , toString = MeasureSpec: EXACTLY 1080
         widthMeasureSpec = 1073742904 , measureWidth = 1080 , modeWidth = 1073741824 , toString = MeasureSpec: EXACTLY 1080
         widthMeasureSpec = 1073742904 , measureWidth = 1080 , modeWidth = 1073741824 , toString = MeasureSpec: EXACTLY 1080*/

        /*<com.example.lyf.androidlyfcode.Measure.MeasureView
        android:id="@+id/measureview"
        android:layout_width="wrap_content"
        android:layout_height="100dp" />*/

/*       widthMeasureSpec = -2147482568 , measureWidth = 1080 , modeWidth = -2147483648 , toString = MeasureSpec: AT_MOST 1080
        widthMeasureSpec = -2147482568 , measureWidth = 1080 , modeWidth = -2147483648 , toString = MeasureSpec: AT_MOST 1080
        widthMeasureSpec = -2147482568 , measureWidth = 1080 , modeWidth = -2147483648 , toString = MeasureSpec: AT_MOST 1080*/

    /*  <com.example.lyf.androidlyfcode.Measure.MeasureView
            android:id="@+id/measureview"
            android:layout_width="100dp"
            android:layout_height="100dp" />*/

 /*      widthMeasureSpec = 1073742124 , measureWidth = 300 , modeWidth = 1073741824 , toString = MeasureSpec: EXACTLY 300
       widthMeasureSpec = 1073742124 , measureWidth = 300 , modeWidth = 1073741824 , toString = MeasureSpec: EXACTLY 300
       widthMeasureSpec = 1073742124 , measureWidth = 300 , modeWidth = 1073741824 , toString = MeasureSpec: EXACTLY 300*/

        Log.e("Test", "widthMeasureSpec = " + widthMeasureSpec + " , measureWidth = "
                + measureWidth + " , modeWidth = " + modeWidth + " , toString = " + MeasureSpec.toString(widthMeasureSpec));
        setMeasuredDimension(measureWidth, measureHeight);

    }

    //2. 分别计算出宽高，后面解释使用的方法的作用
    private int measureWidth(int pWidthMeasureSpec) {
        int result = 0;
        int widthMode = MeasureSpec.getMode(pWidthMeasureSpec);
        int widthSize = MeasureSpec.getSize(pWidthMeasureSpec);
        switch (widthMode) {//规格，决定在容器占的位置
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = widthSize;
                break;
        }
        return result;
    }

    private int measureHeight(int pHeightMeasureSpec) {
        int result = 0;
        int heightMode = MeasureSpec.getMode(pHeightMeasureSpec);
        int heightSize = MeasureSpec.getSize(pHeightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = heightSize;
                break;
        }
        return result;
    }

}
