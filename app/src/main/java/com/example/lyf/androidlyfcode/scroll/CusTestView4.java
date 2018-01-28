//package com.example.lyf.androidlyfcode.scroll;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewConfiguration;
//import android.widget.Scroller;
//
///**
// * Created by xiaolin on 2018/1/27.
// */
//public class CusTestView4 extends View{
//
//	private static final String TAG = "TestView";
//	Scroller mScroller;
//	Paint mPaint;
//
//	public CusTestView4(Context context) {
//		super(context);
//	}
//
//	public CusTestView4(Context context, @Nullable AttributeSet attrs) {
//		super(context, attrs);
//	}
//
//	public CusTestView4(Context context, AttributeSet attrs, int defStyleAttr) {
//		super(context, attrs, defStyleAttr);
//		mPaint = new Paint();
//		mPaint.setAntiAlias(true);
//		mPaint.setColor(Color.RED);
//
//		mScroller = new Scroller(context);
//		//获取最小能够识别的滑动距离
//		mSlop = ViewConfiguration.getTouchSlop();
//		setClickable(true);
//	}
//
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//
//		switch (event.getAction())
//		{
//			case MotionEvent.ACTION_DOWN:
//				restoreTouchPoint(event);
//				break;
//
//			case MotionEvent.ACTION_MOVE:
//				int deltaX = (int) (event.getX() - mLastPointX);
//				int deltaY = (int) (event.getY() - mLastPointY);
//				if(Math.abs(deltaX) > mSlop || Math.abs(deltaY) > mSlop) {
//					//取值的正负与手势的方向相反，这在前面的文章已经解释过了
//					scrollBy(-deltaX,-deltaY);
//					restoreTouchPoint(event);
//				}
//				break;
//
//			case MotionEvent.ACTION_UP:
//				break;
//
//			default:
//				break;
//		}
//
//		return true;
//	}
//
//	private void restoreTouchPoint(MotionEvent event) {
//		mLastPointX = event.getX();
//		mLastPointY = event.getY();
//	}
//
//	public void startScrollBy(int dx,int dy) {
//
//		mScroller.forceFinished(true);
//		int startX = getScrollX();
//		int startY = getScrollY();
//		mScroller.startScroll(startX,startY,startX+dx,startY+dy,1000);
//		invalidate();
//	}
//
//	@Override
//	public void computeScroll() {
//		super.computeScroll();
//		if (mScroller.computeScrollOffset()) {
//
//			scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
//			if (mScroller.getCurrX() == getScrollX()
//					&& mScroller.getCurrY() == getScrollY() ) {
//				postInvalidate();
//			}
//		}
//	}
//}
