package com.example.lyf.androidlyfcode.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * 单位工具转换类
 *
 * @author Administrator
 */
public class WindowUtils {
	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
	//############### TODO android应用程序中获取view的位置
	/**
	 *
	 *  获取View类界面控件的位置，有助于添加新的控件。
	 //#########################################
	 1、 获取在parent里的相对坐标位置:TODO 直接调用View的方法：getLeft , getTop, getBottom, getRight 获得。
	 2、获取在屏幕中的绝对位置 :
	 ################ TODO 2.1 getLocalVisibleRect
	 ################ TODO 2.2getGlobalVisibleRect
	 ################TODO 2.3getLocationOnScreen
	 ################ TODO 2.4getLocationInWindow
	 */
	//########################################################

	/**
	 * 这个方法是将view的左上角坐标存入数组中.此坐标是相对当前activity而言.
	 * 若是普通activity,则y坐标为可见的状态栏高度+可见的标题栏高度+view左上角到标题栏底部的距离.
	 * 可见的意思是:在隐藏了状态栏/标题栏的情况下,它们的高度以0计算.
	 * 若是对话框式的activity,则y坐标为可见的标题栏高度+view到标题栏底部的距离.
	 * 此时是无视状态栏的有无的.
	 *
	 * @param view
	 */
	public static void getLocationInWindow(View view) {
		int[] position = new int[2];
		view.getLocationInWindow(position);
		System.out.println("getLocationInWindow:" + position[0] + "," + position[1]);
	}

	/**
	 * 这个方法跟getLocationInWindow差不多,也是将view的左上角坐标存入数组中.但此坐标是相对整个屏幕而言.
	 * y坐标为view左上角到屏幕顶部的距离.
	 *
	 * @param view
	 */
	public static void getLocationOnScreen(View view) {
		int[] position = new int[2];
		view.getLocationOnScreen(position);
		System.out.println("getLocationOnScreen:" + position[0] + "," + position[1]);
	}

	/**
	 * 这个方法是构建一个Rect用来"套"这个view.此Rect的坐标是相对当前activity而言.
	 * 若是普通activity,则Rect的top为可见的状态栏高度+可见的标题栏高度+Rect左上角到标题栏底部的距离.
	 * 若是对话框式的activity,则y坐标为Rect的top为可见的标题栏高度+Rect左上角到标题栏底部的距离.
	 * 此时是无视状态栏的有无的.
	 *
	 * @param view
	 */
	public static void getGlobalVisibleRect(View view) {
		Rect viewRect = new Rect();
		view.getGlobalVisibleRect(viewRect);
		System.out.println(viewRect);
	}

	public static void getLocalVisibleRect(final View view, final ScrollView mTreeScrollView) {
		Rect globeRect = new Rect();
		view.getLocalVisibleRect(globeRect);
		/**
		 *注意:
		 以上方法在OnCreate方法中调用,都会返回0,这是因为View还未加载完毕.
		 建议在onWindowFocusChanged方法中进行获取,有些情况下onWindowFocusChanged不好用的时候(比如ActivityGroup),可以这样写:
		 */
		view.post(new Runnable() {
			@Override
			public void run() {
				Rect viewRect = new Rect();
				view.getLocalVisibleRect(viewRect);
//				mTreeScrollView.setRect(viewRect);
			}
		});
	}
}
