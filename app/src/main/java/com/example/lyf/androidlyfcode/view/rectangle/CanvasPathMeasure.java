package com.example.lyf.androidlyfcode.view.rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by xiaolin on 2018/1/9.
 */
public class CanvasPathMeasure extends View {
	public CanvasPathMeasure(Context context) {
		super(context);
	}

	public CanvasPathMeasure(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public CanvasPathMeasure(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	void draw1(Canvas canvas) {
/*		Path & PathMeasure
		顾名思义，PathMeasure是一个用来测量Path的类，主要有以下方法:
		构造方法
		PathMeasure() 	创建一个空的PathMeasure
		PathMeasure(Path path, boolean forceClosed) 创建 PathMeasure 并关联一个指定的Path(Path需要已经创建完成)
		void 	setPath(Path path, boolean forceClosed) 	关联一个Path
		boolean 	isClosed() 	是否闭合
		float 	getLength() 	获取Path的长度
		boolean 	nextContour() 	跳转到下一个轮廓
		boolean 	getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo) 	截取片段
		boolean 	getPosTan(float distance, float[] pos, float[] tan) 	获取指定长度的位置坐标及该点切线值
		boolean 	getMatrix(float distance, Matrix matrix, int flags) 	获取指定长度的位置坐标及该点Matrix


		1.无参构造函数：
				  PathMeasure ()
				  用这个构造函数可创建一个空的 PathMeasure，但是使用之前需要先调用 setPath 方法来与 Path 进行关联。
				  被关联的 Path 必须是已经创建好的，如果关联之后 Path 内容进行了更改，则需要使用 setPath 方法重新关联。

		2.有参构造函数：
				   PathMeasure (Path path, boolean forceClosed)
				   用这个构造函数是创建一个 PathMeasure 并关联一个 Path， 其实和创建一个空的 PathMeasure 后调用 setPath 进行关联效果是一样的，
				   同样，被关联的 Path 也必须是已经创建好的，如果关联之后 Path 内容进行了更改，则需要使用 setPath 方法重新关联。

		该方法有两个参数，第一个参数自然就是被关联的 Path 了，第二个参数是用来确保 Path 闭合，
		如果设置为 true， 则不论之前Path是否闭合，都会自动闭合该 Path(如果Path可以闭合的话)。

		在这里有两点需要明确:

          1.  不论 forceClosed 设置为何种状态(true 或者 false)， 都不会影响原有Path的状态，
            即 Path 与 PathMeasure 关联之后，之前的的 Path 不会有任何改变。
          2.  forceClosed 的设置状态可能会影响测量结果，如果 Path 未闭合但在与 PathMeasure 关联的时候
          设置 forceClosed 为 true 时，测量结果可能会比 Path 实际长度稍长一点，获取到到是该 Path 闭合时的状态。
		*/
		canvas.translate(getWidth() / 2, getHeight() / 2);
		Path path = new Path();
		path.lineTo(0, 200);
		path.lineTo(200, 200);
		path.lineTo(200, 0);
		PathMeasure measure1 = new PathMeasure(path, false);
		PathMeasure measure2 = new PathMeasure(path, true);
		Log.e("TAG", "forceClosed=false---->" + measure1.getLength());
		Log.e("TAG", "forceClosed=true----->" + measure2.getLength());
//		canvas.drawPath(path, mDeafultPaint);
		/**
		 1.我们将 Path 与两个的 PathMeasure 进行关联，并给 forceClosed 设置了不同的状态，
		 之后绘制再绘制出来的 Path 没有任何变化，所以与 Path 与 PathMeasure进行关联并不会影响 Path 状态。
		 2.我们可以看到，设置 forceClosed 为 true 的方法比设置为 false 的方法测量出来的长度要长一点，
		 这是由于 Path 没有闭合的缘故，多出来的距离正是 Path 最后一个点与最开始一个点之间点距离。
		 forceClosed 为 false 测量的是当前 Path 状态的长度， forceClosed 为 true，则不论Path是否闭合测量的都是 Path 的闭合长度。
		 */
	}
}
