package com.example.lyf.androidlyfcode.view.rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiaolin on 2018/1/9.
 */
public class CanvasPathEnd extends View {
	Paint mDeafultPaint;

	public CanvasPathEnd(Context context) {
		super(context);
	}

	public CanvasPathEnd(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public CanvasPathEnd(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mDeafultPaint = new Paint();             // 创建画笔
		mDeafultPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
		mDeafultPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
		mDeafultPaint.setStrokeWidth(10);              // 边框宽
	}

	//rXxx方法
	void draw1(Canvas canvas) {
		//rXxx方法的坐标使用的是相对位置(基于当前点的位移)，而之前方法的坐标是绝对位置(基于当前坐标系的坐标)。
//		Path path = new Path();
//		path.moveTo(100, 100);
//		path.lineTo(100, 200);
//		canvas.drawPath(path, mDeafultPaint);
		//####
		Path path = new Path();
		path.moveTo(100, 100);
		path.rLineTo(100, 200);
		canvas.drawPath(path, mDeafultPaint);
		/**
		 * 将 lineTo 换成了 rLineTo 可以看到在屏幕上原本是竖直的线变成了倾斜的线。
		 * 这是因为最终我们连接的是 (100,100) 和 (200, 300) 之间的线段。
		 在使用rLineTo之前，当前点的位置在 (100,100) ， 使用了 rLineTo(100,200) 之后，
		 下一个点的位置是在当前点的基础上加上偏移量得到的，即 (100+100, 100+200) 这个位置，故最终结果如上所示。
		 PS: 此处仅以 rLineTo 为例，只要理解 “绝对坐标” 和 “相对坐标” 的区别，其他方法类比即可。
		 */
	}
}
