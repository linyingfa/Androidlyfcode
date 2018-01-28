package com.example.lyf.androidlyfcode.scroll;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiaolin on 2018/1/27.
 */
public class CusTestView2 extends View {
	private static final String TAG = "TestView";
	Paint mPaint;

	public CusTestView2(Context context) {
		this(context, null);
	}

	public CusTestView2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CusTestView2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.RED);
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

	public void startGunDong(int dx, int dy) {
		int startX = getScrollX();
		int startY = getScrollY();
		//scrollBy
		PropertyValuesHolder xholder = PropertyValuesHolder.ofInt("scrollX", startX, startX + dx);
		PropertyValuesHolder yholder = PropertyValuesHolder.ofInt("scrollY", startY, startY + dy);
		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(this, xholder, yholder);
		animator.setDuration(1000);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				invalidate();
			}
		});
		animator.start();
	}
}
