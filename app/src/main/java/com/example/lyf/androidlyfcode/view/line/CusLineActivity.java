package com.example.lyf.androidlyfcode.view.line;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.lyf.androidlyfcode.R;

/**
 * Created by Administrator on 2019/1/11.
 */

public class CusLineActivity extends Activity {
    CusLine cusLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cusline);
        cusLine = findViewById(R.id.cusLine);
    }
}
