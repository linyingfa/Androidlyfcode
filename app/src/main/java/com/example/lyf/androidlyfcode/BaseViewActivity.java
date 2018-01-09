package com.example.lyf.androidlyfcode;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.lyf.androidlyfcode.view.viewbase.DrawBaseView;
import com.example.lyf.androidlyfcode.view.viewbase.DrawPathView;
import com.example.lyf.androidlyfcode.view.viewbase.DrawTextView;

/**
 * Created by xiaolin on 2018/1/8.
 */
public class BaseViewActivity extends Activity {
	DrawBaseView drawBaseView;
	DrawTextView drawTextView;
	DrawPathView drawPathView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baseview);
		drawBaseView = (DrawBaseView) this.findViewById(R.id.baseview);
		drawTextView = (DrawTextView) this.findViewById(R.id.textview);
		drawPathView = (DrawPathView) this.findViewById(R.id.pathview);
	}

	public void baseview(View view) {
		drawBaseView.setVisibility(View.VISIBLE);
		drawTextView.setVisibility(View.GONE);
		drawPathView.setVisibility(View.GONE);
	}

	public void textview(View view) {
		drawBaseView.setVisibility(View.GONE);
		drawTextView.setVisibility(View.VISIBLE);
		drawPathView.setVisibility(View.GONE);
	}

	public void pathview(View view) {
		drawBaseView.setVisibility(View.GONE);
		drawTextView.setVisibility(View.GONE);
		drawPathView.setVisibility(View.VISIBLE);
	}
}
