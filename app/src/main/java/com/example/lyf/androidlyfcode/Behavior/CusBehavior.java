package com.example.lyf.androidlyfcode.Behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2019/1/10.
 * 注意不要忘了重写两个参数的构造函数，否则无法在xml文件中使用该Behavior
 */
public class CusBehavior extends CoordinatorLayout.Behavior {

    // 列表顶部和title底部重合时，列表的滑动距离。
    private float deltaY;

    public CusBehavior() {
    }

    public CusBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * layoutDependsOn()：使用该Behavior的View要监听哪个类型的View的状态变化。
     *
     * @param parent     代表CoordinatorLayout
     * @param child      代表使用该Behavior的View
     * @param dependency 代表要监听的View。这里要监听RecyclerView。
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    /**
     * 当被监听的View状态变化时会调用该方法，参数和上一个方法一致。所以我们重写该方法，当RecyclerView的位置变化时，进而改变title的位置。
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }
        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        float y = -(dy / deltaY) * child.getHeight();
        child.setTranslationY(y);
        return true;
    }

}
