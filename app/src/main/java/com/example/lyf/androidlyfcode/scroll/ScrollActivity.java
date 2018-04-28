package com.example.lyf.androidlyfcode.scroll;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.lyf.androidlyfcode.R;

/**
 * Created by xiaolin on 2018/1/27.
 */
public class ScrollActivity extends Activity {
	CusTestView mTestView;
	CusTestView2 cusTestView2;
	CusTestView3 cusTestView3;
	Button mBtnTest;

	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scroll);
		mTestView = findViewById(R.id.testView);
		cusTestView2 = findViewById(R.id.cusTestView2);
		cusTestView3 = findViewById(R.id.cusTestView3);
		mBtnTest = findViewById(R.id.btn_test);
		/**
		 * //TODO 编写一些自定义的滑动控件时，会用到一些api如scrollTo(),scrollBy(),getScrollX(), getScrollY()。
		 * 注意：调用View的scrollTo()和scrollBy()是用于滑动View中的内容，
		 * 而不是把某个View的位置进行改变。如果想改变莫个View在屏幕中的位置，可以使用如下的方法。
		 调用public void offsetLeftAndRight(int offset)用于左右移动方法
		 public void offsetTopAndBottom(int offset)用于上下移动。
		 如：button.offsetLeftAndRignt(300)表示将button控件向左移动300个像素。
		 TODO scrollTo(int x, int y) 是将View中内容滑动到相应的位置，参考的坐标系原点为parent View的左上角。
		 TODO 例如，scrollview 固定，滚动的是子view的相对应的位置，而不是这个scrollview的位置
		 */
		mBtnTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO View的scrollTo()、scrollBy()，scrollBy(int x, int y)其实是对scrollTo的包装，移动的是相对位置
				//TODO scrollTo、scrollBy滑动的是View中的内容（而且还是整体滑动），而不是View本身
				//TODO View的scrollTo、scrollBy方法，相当于是移动滑动控件中的画布Canvas，然后进行重绘，屏幕上也就显示相应的内容
				mTestView.scrollBy(-1 * 150, -1 * 150);
				cusTestView2.startGunDong(-1 * 100, -1 * 100);//通过动画
				cusTestView3.startScrollBy(-1 * 100, -1 * 100);//通过Scroller类
			}
		});
	}
}
