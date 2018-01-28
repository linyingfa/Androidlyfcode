package com.example.lyf.androidlyfcode.scroll;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by xiaolin on 2018/1/27.
 * 编写一些自定义的滑动控件时，会用到一些api如scrollTo(),scrollBy(),getScrollX(), getScrollY()。
 */
public class CusTestView3 extends View {
	private static final String TAG = "TestView";
	Scroller mScroller;
	Paint mPaint;

	public CusTestView3(Context context) {
		this(context, null);
	}

	public CusTestView3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CusTestView3(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.RED);
		mScroller = new Scroller(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.GRAY);
		canvas.save();
		canvas.translate(100, 100);
		canvas.drawCircle(0, 0, 40.0f, mPaint);
		canvas.drawCircle(50, 50, 40.0f, mPaint);
		canvas.restore();
	}

	public void startScrollBy(int dx, int dy) {
		mScroller.forceFinished(true);
		int startX = getScrollX();
		int startY = getScrollY();
		mScroller.startScroll(startX, startY, startX + dx, startY + dy, 1000);
		invalidate();
	}
//	这篇文章的主要内容可以总结如下：
//
//	1、View 滑动的基础是 mScrollX 和 mScrollY 两个属性。
//	2、Android 系统处理滑动时会在 onDraw(Canvas canvas) 方法之前，对 canvas 对象进行平移，
//			canvas.translate(mLeft-mScrollX,mRight-mScrollY)。平移的目的是将坐标系从 ViewGroup 转换到 child 中。
//	3、调用一个 View 的滑动有 scrollBy() 和 scrollTo() 两种，前一种是增量式，后一种直接到位。
//	4、如果要实现平滑滚动的效果，不借助于 Scroller 而自己实现属性动画也是可以完成的，
//			原因还是针对 mScrollX 或者 mScrollY 的变化引起的重绘。
//	5、View 滚动的区域是内容，也就是它绘制的内容，而对于一个 ViewGroup 而言，
//				它的内容还包括它的 children。所以，如果想移动一个 View，本身那么就应该调用它的 parent 的 scrollBy() 或者 scrollTo() 方法。
//	6、Scroller 本身并不神秘与复杂，它只是模拟提供了滚动时相应数值的变化，
//				复写自定义 View 中的 computeScroll() 方法，
//				在这里获取 Scroller 中的 mCurrentX 和 mCurrentY，
//				根据自己的规则调用 scrollTo() 方法，就可以达到平稳滚动的效果。
//	7、Scroller 提供快速滚动的功能，需要在自定义 View 的 onTouchEvent() 方法中获取相应方向的初始速度，
//			然后调用 Scroller 的 startFling() 方法。
//	8、最重要的一点就是要深刻理解 mScrollX、mScrollY 在 Canvas 坐标中的意义，
//		要区分手指滑动方向、内容滑动方向和 mScrollX、mScrollY 数值的关系。

	@Override
	public void computeScroll() {
		super.computeScroll();
		// 如果动画正在进行中，则返回 true，否则返回 false。
		// 我们只需要针对 Scroller 正在运行的状态
		if (mScroller.computeScrollOffset()) {
			/**
			 *
			 * // 通过获取 Scroller 中 mCurrentX、mCurrentY 的值，直接设置为 mScrollX、mScrollY
			 // 在实际开发中，mCurrentX、mCurrentY 与 mScrollX、mScrollY 的关系由开发者自己定义
			 *TODO  Scroller类中最重要的两个方法就是startScroll()和computeScrollOffset()，但是Scroller类只是一个滑动计算辅助类，
			 *TODO 它的startScroll()和computeScrollOffset()方法中也只是对一些轨迹参数进行设置和计算，
			 *TODO 真正需要进行滑动还是得通过View的scrollTo()、scrollBy()方法。
			 */
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			if (mScroller.getCurrX() == getScrollX() && mScroller.getCurrY() == getScrollY()) {
				postInvalidate();
			}
		}
	}
}
