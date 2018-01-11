package com.example.lyf.androidlyfcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lyf.androidlyfcode.view.circle.CircleAlarmTimerView;
import com.example.lyf.androidlyfcode.view.circle.StegePointProcessView;
import com.google.gson.Gson;

/**
 * Created by huangweizhu on 2018/1/10.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class BlendViewActivity extends AppCompatActivity {

    StegePointProcessView stegePointProcessView;
    private TextView textView1;
    private TextView textView2;
    private CircleAlarmTimerView circleAlarmTimerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bleandview);
        stegePointProcessView = (StegePointProcessView) this.findViewById(R.id.stegePointProcessView);
        stegePointProcessView.setMaxProcess(30);
        stegePointProcessView.setTips(7, 15, 30);
        stegePointProcessView.setCurProcess(20);
        Gson gson = new Gson();
        initView();
    }

    private void initView() {

        textView1 = (TextView) findViewById(R.id.start);
        textView2 = (TextView) findViewById(R.id.end);

        circleAlarmTimerView = (CircleAlarmTimerView) findViewById(R.id.circletimerview);
        circleAlarmTimerView.setOnTimeChangedListener(new CircleAlarmTimerView.OnTimeChangedListener() {
            @Override
            public void start(String starting) {
                textView1.setText(starting);
            }

            @Override
            public void end(String ending) {
                textView2.setText(ending);
            }
        });
    }
}
