package com.example.lyf.androidlyfcode;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.lyf.androidlyfcode.fragment.FragmentActivity;
import com.example.lyf.androidlyfcode.scroll.ScrollActivity;
import com.example.lyf.androidlyfcode.sonic.SonicTestActivity;
import com.example.lyf.androidlyfcode.utils.Intents;
import com.example.lyf.androidlyfcode.view.rectangle.RectangleActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static List<String> nameList = new ArrayList<>();

    static {
        nameList.add("base图形");
        nameList.add("测量相关");
        nameList.add("绘画矩形");
        nameList.add("进度条");
        nameList.add("Sonic");
        nameList.add("ScrollActivity");
        nameList.add("FragmentActivity");
    }

    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new GridViewAdapter(nameList, this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intents.getIntents().Intent(MainActivity.this, BaseViewActivity.class, null);
                        break;
                    case 1:
                        Intents.getIntents().Intent(MainActivity.this, ViewStudy.class, null);
                        break;
                    case 2:
                        Intents.getIntents().Intent(MainActivity.this, RectangleActivity.class, null);
                        break;
                    case 3:
                        Intents.getIntents().Intent(MainActivity.this, BlendViewActivity.class, null);
                        break;
                    case 4:
                        Intents.getIntents().Intent(MainActivity.this, SonicTestActivity.class, null);
                        break;
                    case 5:
                        Intents.getIntents().Intent(MainActivity.this, ScrollActivity.class, null);
                        break;
                    case 6:
                        Intents.getIntents().Intent(MainActivity.this, FragmentActivity.class, null);
                        break;
                }
            }
        });
    }

    public static class GridViewAdapter extends BaseAdapter {
        List<String> namelist;
        private Context mContext;

        public GridViewAdapter(List<String> namelist, Context mContext) {
            this.namelist = namelist;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return namelist == null ? 0 : namelist.size();
        }

        @Override
        public Object getItem(int position) {
            return namelist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.gridview_item, parent, false);
            TextView textView = view.findViewById(R.id.item_tv);
            textView.setText(namelist.get(position));
            return view;
        }
    }
}
