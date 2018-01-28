package com.example.lyf.androidlyfcode.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiaolin on 2018/1/28.
 */
public class MyViewPager extends ViewGroup {
	private int mLastX;

	public MyViewPager(Context context) {
		super(context);
		init(context);
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int count = getChildCount();//会执行多次这个测试方法
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			//告诉系统每个child需要多少宽高
			child.measure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();
		Log.d("TAG", "--l-->" + l + ",--t-->" + t + ",-->r-->" + r + ",--b-->" + b);
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			child.layout(i * getWidth(), t, (i + 1) * getWidth(), b);
		}
	}

	/**
	 * 分为了三步。
	 * 第一是，通过int dy = mLastY - y;得到本次手势在屏幕上滑动了多少距离，这里要特别注意这个相减顺序，
	 * 因为这里的坐标与平常是相反的，因此，手势滑动距离是按下时的坐标mLastY - 当前的坐标y；
	 * 第二是，通过oldScrollY = getScrollY();获得滑动内容之前已经距初始位置便宜了多少；
	 * 第三是，计算本次需要偏移的参数int scrollY = oldScrollY + dy; 后面通过两个if条件进行了边界处理，
	 * 然后调用scrollTo进行滑动。调用完scrollTo后，新的偏移量又重新产生了
	 *
	 * @param ev
	 * @return
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int x = (int) ev.getX();
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mLastX = x;//记录每次手指按下的坐标
				break;
			case MotionEvent.ACTION_MOVE:
				int dx = mLastX - x;//得到本次手势在屏幕上滑动了多少距离
				int oldScrollX = getScrollX();//先计算之前已经偏移了多少距离
				int preScrollX = oldScrollX + dx;//本次需要偏移的距离=之前已经偏移的距离+本次手势滑动了多大距离
				if (preScrollX > (getChildCount() - 1) * getWidth()) {
					preScrollX = (getChildCount() - 1) * getWidth();
				}
				if (preScrollX < 0) {
					preScrollX = 0;
				}
				//TODO scrollTo()、scrollBy()
				scrollTo(preScrollX, getScrollY());
				mLastX = x;
				break;
		}
		return true;
	}
	//TODO #############################################
	//TODO #############################################
	//TODO #############################################
	/**
	 * 从scrollTo源码中可以看到：
	 * scrollTo是相对于初始位置来进行移动的，而scrollBy(int x ,int y)则是相对于上一次移动的距离来进行本次移动。
	 * scrollBy其实还是依赖于scrollTo的，如下源码：
	 */
	//TODO #############################################
	//使用scrollBy其实就是省略了我们在计算scrollTo参数时的第三步而已，因为scrollBy内部已经自己帮我加上了第三步的计算。
	// 因此scrollBy的作用就是相当于在上一次的偏移情况下进行本次的偏移。
	//TODO #############################################
//	public void scrollBy(int x, int y) {
//		scrollTo(mScrollX + x, mScrollY + y);
//	}
	//TODO #############################################
	//TODO scrollTo是相对于【初始位置】来进行移动的
	// TODO 而scrollBy(int x ,int y)则是【相对】于上一次移动的距离来进行本次移动。
	//TODO  scrollBy其实还是依赖于scrollTo的，如下源码：
	//TODO #############################################
//	public void scrollTo(int x, int y) {
//		if (mScrollX != x || mScrollY != y) {
//			int oldX = mScrollX;
//			int oldY = mScrollY;
//			mScrollX = x;//赋值新的x偏移量
//			mScrollY = y;//赋值新的y偏移量
//			invalidateParentCaches();
//			onScrollChanged(mScrollX, mScrollY, oldX, oldY);
//			if (!awakenScrollBars()) {
//				postInvalidateOnAnimation();
//			}
//		}
//	}
}
