package com.example.lyf.androidlyfcode.Behavior;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lyf.androidlyfcode.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/10.
 */

public class CusBehaviorActivity extends Activity {
    RecyclerView recyclerView;
    static List<String> strings = new ArrayList<>();

    static {
        for (int i = 0; i < 20; i++) {
            strings.add("");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybehavior);
        recyclerView = findViewById(R.id.my_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter(strings));

    }
}
