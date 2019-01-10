package com.example.lyf.androidlyfcode.Measure;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/1/10.
 */

public class MeasureViewGroup extends ViewGroup {
    public MeasureViewGroup(Context context) {
        super(context);
    }

    public MeasureViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
    }


    /* changed = true, left = 0, top = 0, right = 1080, bottom = 1740, measureWidth = 130, measureHieght = 130
    changed = true, left = 0, top = 0, right = 1080, bottom = 1740, measureWidth = 80, measureHieght = 80
     changed = false, left = 0, top = 0, right = 1080, bottom = 1740, measureWidth = 130, measureHieght = 130
    changed = false, left = 0, top = 0, right = 1080, bottom = 1740, measureWidth = 80, measureHieght = 80*/

//    3.1 onLayout回调函数参数：
//    left, top, right, bottom是当前ViewGroup整个在屏幕上的位置，手机屏幕是480 * 800 ，其中高度去除状态栏和title大概724
//    如果页面只有此ViewGroup则个参数的值,left = 0, top = 0, right = 480, bottom = 724

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int mTotalHeight = 0;
        // 遍历所有子视图
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
//            × 三个方法的区别？后这个方法最终还是调用第一个方法
//            childView.measure(widthMeasureSpec, heightMeasureSpec);
//            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
//            measureChildren(widthMeasureSpec, heightMeasureSpec);
//          × 哪些视图属性影响 measure？      padding、margin
//          × 哪些视图属性影响 layout？        gravity
//          × view的上下左右和measurespec width和height的关系 ？
            View childView = getChildAt(i);
            // 获取在onMeasure中计算的视图尺寸
            int measureHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();
            childView.layout(l, mTotalHeight, measuredWidth, mTotalHeight + measureHeight);
            mTotalHeight += measureHeight;
            Log.e("TAG", "changed = " + changed
                    + ", left = " + l + ", top = " + t
                    + ", right = " + r + ", bottom = " + b
                    + ", measureWidth = " + measuredWidth + ", measureHieght = " + measureHeight);
        }

    }
}
