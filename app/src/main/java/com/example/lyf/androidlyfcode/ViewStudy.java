package com.example.lyf.androidlyfcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by huangweizhu on 2018/1/8.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class ViewStudy extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstudy);

        //
        WindowManager manager = getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;//1080
        int height = outMetrics.heightPixels;//1804
//		01-02 22:09:39.501 10087-10087/lyfproject.com.my530view I/width: width=1080
//		01-02 22:09:39.501 10087-10087/lyfproject.com.my530view I/height: height=1812
        Log.i("width", "width=" + width);
        Log.i("height", "height=" + height);
    }
}
