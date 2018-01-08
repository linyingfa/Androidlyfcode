package com.example.lyf.androidlyfcode.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.lyf.androidlyfcode.R;
import com.example.lyf.androidlyfcode.utils.WindowUtils;


/**
 * Created by huangweizhu on 2018/1/8.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 * padding的处理
 * TODO 自定义View中的处理
 * TODO 自定义ViewGroup中的处理
 * margin的处理
 * TODO 自定义ViewGroup中的处理
 * 总结一下：
 * <p>
 * 在自定义View中处理padding，只需要在onDraw()中处理，别忘记处理布局为wrap_content的情况。
 * 在自定义ViewGroup中处理padding，只需要在onLayout()中，给子View布局时算上padding的值即可，也别忘记处理布局为wrap_content的情况。
 * 自定义View无需处理margin，在自定义ViewGroup中处理margin时，需要在onMeasure()中根据margin计算ViewGroup的宽、高，
 * 同时在onLayout中布局子View时也别忘记根据margin来布局。
 */

public class PaddingMarginView extends View {

    private Paint mPaint;
    private int color;
    private int width;
    private int height;

    //
    int paddingLeft;
    int paddingTop;
    int paddingRight;
    int paddingBottom;

    private Context mContext;

    public PaddingMarginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        mContext = context;
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PaddingMarginView);
        color = array.getColor(R.styleable.PaddingMarginView_backgroundColor, Color.BLUE);
        array.recycle();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = measureWidth(widthMeasureSpec);
        height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

/*
    //如果这样直接绘制，在xml上添加padding是没有效果的
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        rect.left = 0;
        rect.top = 0;
        rect.right = width;
        rect.bottom = height;
        canvas.drawRect(rect, mPaint);
    }*/


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paddingLeft = getPaddingLeft();
        paddingTop = getPaddingTop();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();
        Log.i("padding",
                "paddingLeft=" + WindowUtils.px2dp(mContext, paddingLeft)
                        + "\n---paddingTop=" + WindowUtils.px2dp(mContext, paddingTop)
                        + "\npaddingRight=" + WindowUtils.px2dp(mContext, paddingRight)
                        + "\npaddingBottom=" + WindowUtils.px2dp(mContext, paddingRight));
        Rect rect = new Rect();
        rect.left = 0 + paddingLeft;
        rect.top = 0 + paddingTop;
        rect.right = width - paddingRight;
        rect.bottom = height - paddingBottom;
        canvas.drawRect(rect, mPaint);
    }

    private int measureWidth(int measureSpec) {
        int result = 200;
        result = WindowUtils.dp2px(mContext, 200);
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 200;
        result = WindowUtils.dp2px(mContext, 200);
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }
}
