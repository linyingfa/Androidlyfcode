package com.example.lyf.androidlyfcode.Behavior;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lyf.androidlyfcode.R;

import java.util.List;

/**
 * Created by Administrator on 2019/1/10.
 */

public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyAdapter(@Nullable List<String> data) {
        super(R.layout.item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
