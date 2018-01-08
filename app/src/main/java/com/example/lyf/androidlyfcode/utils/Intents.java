package com.example.lyf.androidlyfcode.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by huangweizhu on 2017/11/30.
 * e-mail:huangweizhu8@163.com
 * version: 2.0
 * Describe:
 */

public class Intents {

    private static Intents intents;

    public static Intents getIntents() {
        if (intents == null)
            intents = new Intents();
        return intents;
    }

    // context this, cs跳转对象 bundle 传递参数
    public void Intent(Context context, Class<?> cs, Bundle bundle) {
        Intent i = new Intent(context, cs);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        context.startActivity(i);
    }

}
