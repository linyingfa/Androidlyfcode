package com.example.lyf.androidlyfcode.view.layoutmesumt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.lyf.androidlyfcode.utils.WindowUtils;

/**
 * Created by xiaolin on 2018/1/2.
 */
public class Circle530 extends View {
	//画笔
	private Paint mPaint;
	private Paint mPaintline;
	private int radius;
	private Context mContext;

	public Circle530(Context context) {
		super(context);
		initConfig(context);
	}

	public Circle530(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initConfig(context);
	}

	public Circle530(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initConfig(context);
	}

	void initConfig(Context mContext) {
		this.mContext = mContext;
		mPaint = new Paint();
		mPaint.setColor(Color.RED);
		mPaint.setAntiAlias(true);
		//
		mPaintline = new Paint();
		mPaintline.setColor(Color.RED);
		mPaintline.setAntiAlias(true);
		mPaintline.setStrokeWidth(WindowUtils.dp2px(mContext, 6));
		//
		radius = WindowUtils.dp2px(mContext, 20);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	/**
	 * <lyfproject.com.my530view.view.Circle530
	 * android:id="@+id/cirlcle530"
	 * android:layout_width="wrap_content"
	 * android:layout_height="wrap_content" />
	 * 1.当我们把布局文件的宽和高写成wrap_content，会占满全屏。
	 * 系统帮我们测量的高度和宽度都是MATCH_PARNET，当我们设置【明确】的宽度和高度时，系统帮我们测量的结果就是我们设置的结果，
	 * 当我们设置为WRAP_CONTENT,或者MATCH_PARENT【系统帮我们测量的结果】就是MATCH_PARENT的长度。
	 * * <p>
	 * MeasureSpec的specMode,一共三种类型：
	 * EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
	 * <p>
	 * AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
	 * <p>
	 * UNSPECIFIED：表示子布局想要多大就多大，很少使用
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int width = 0, height = 0;
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;//屏幕可见的宽度
		} else {
			//
			width = WindowUtils.dp2px(mContext, 200);
		}
		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;//屏幕可见的高度
		} else {
			//
			height = WindowUtils.dp2px(mContext, 200);
		}
		setMeasuredDimension(width, height);
	}

	/**
	 * //注意：View的坐标系统是相对于父控件而言的.
	 * 列如，固定了200,200.那么0,0，就是在200,200这个区域开始
	 *
	 *
	 * @param canvas
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//系统的get方法单位都是px
		int top = getTop();       //获取子View左上角距父View顶部的距离
		int left = getLeft();      //获取子View左上角距父View左侧的距离
		int bottom = getBottom();    //获取子View右下角距父View顶部的距离
		int right = getRight();     //获取子View右下角距父View左侧的距离
		Log.i("top", "top=" + WindowUtils.px2dp(mContext, getTop()));
		Log.i("left", "left=" + WindowUtils.px2dp(mContext, getLeft()));
		Log.i("bottom", "bottom=" + WindowUtils.px2dp(mContext, getBottom()));
		Log.i("right", "right=" + WindowUtils.px2dp(mContext, getRight()));
		setBackgroundColor(Color.YELLOW);
		int x = getWidth() / 2;//150, //父控件的宽度
		int y = getHeight() / 2;//150 //父控件的高度
		//圆心坐标，第三个是半径，最后一个是画笔
		canvas.drawCircle(x, y, radius, mPaint);
		//01-02 22:09:39.501 10087-10087/lyfproject.com.my530view I/width: width=1080
//		01-02 22:09:39.501 10087-10087/lyfproject.com.my530view I/height: height=1812
		canvas.drawLine(0, 0, 1080, 0, mPaintline);
		canvas.drawLine(0, 0, 0, 1812, mPaintline);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}
}
