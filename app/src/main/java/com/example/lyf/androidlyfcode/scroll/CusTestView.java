package com.example.lyf.androidlyfcode.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiaolin on 2018/1/27.
 */
public class CusTestView extends View {
	private static final String TAG = "TestView";
	Paint mPaint;

	public CusTestView(Context context) {
		this(context, null);
	}

	public CusTestView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CusTestView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.RED);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.GRAY);
		canvas.drawCircle(0, 0, 40.0f, mPaint);
	}
}
