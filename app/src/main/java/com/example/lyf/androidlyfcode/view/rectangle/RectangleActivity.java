package com.example.lyf.androidlyfcode.view.rectangle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lyf.androidlyfcode.R;

import java.util.ArrayList;

/**
 * Created by huangweizhu on 2018/1/9.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class RectangleActivity extends AppCompatActivity {

    PieView priview;
    CanvasBitmap checkView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangle);
        priview = (PieView) this.findViewById(R.id.priview);
        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("sloop", 60);
        PieData pieData2 = new PieData("sloop", 30);
        PieData pieData3 = new PieData("sloop", 40);
        PieData pieData4 = new PieData("sloop", 20);
        PieData pieData5 = new PieData("sloop", 20);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        priview.setData(datas);

        //
        checkView = (CanvasBitmap) this.findViewById(R.id.checkView);

    }

    public void onClick(View view) {
        checkView.check();
    }
}
