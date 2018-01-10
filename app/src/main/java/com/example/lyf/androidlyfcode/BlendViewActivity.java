package com.example.lyf.androidlyfcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lyf.androidlyfcode.view.circle.StegePointProcessView;

/**
 * Created by huangweizhu on 2018/1/10.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class BlendViewActivity extends AppCompatActivity {

    StegePointProcessView stegePointProcessView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bleandview);
        stegePointProcessView = (StegePointProcessView) this.findViewById(R.id.stegePointProcessView);
        stegePointProcessView.setMaxProcess(30);
        stegePointProcessView.setTips(7, 15, 30);
        stegePointProcessView.setCurProcess(20);
    }
}
