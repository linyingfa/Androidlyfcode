package com.example.lyf.androidlyfcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.example.lyf.androidlyfcode.R;


/**
 * Created by Administrator on 2018/2/9.
 */

public class FragmentActivity extends AppCompatActivity {

    public final static String ATG = "Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_fragment);
        //直接new一个fragment和普通的类实例化没有什么区别，都会走构造方法，但是Fragment的生命周期不会走
        TextFragment textFragment = TextFragment.newInstance("hello world");
        TextFragment2 textFragment2 = TextFragment2.newInstance("hello world");
        getSupportFragmentManager().beginTransaction()
                //getSupportFragmentManager 是FragmentActivity的方法
                .add(R.id.framelayout, textFragment, "f1")
                //.addToBackStack("fname")
                .addToBackStack("").commit();
        //如果没有加入回退栈，则用户点击返回按钮会直接将Activity出栈；如果加入了回退栈，则用户点击返回按钮会回滚Fragment事务。
        getSupportFragmentManager().beginTransaction()
                //getSupportFragmentManager 是FragmentActivity的方法
                .add(R.id.framelayout, textFragment2, "f1")
                //.addToBackStack("fname")
                .addToBackStack("").commit();

        Log.i(ATG, "--->onCreate");

        /**
         * addToBackStack()对应的是popBackStack()，有以下几种变种：
         popBackStack()：将回退栈的栈顶弹出，并回退该事务。
         popBackStack(String name, int flag)：name为addToBackStack(String name)的参数，
         通过name能找到回退栈的特定元素，flag可以为0或者FragmentManager.POP_BACK_STACK_INCLUSIVE，
         0表示只弹出该元素以上的所有元素，不包含本身元素
         POP_BACK_STACK_INCLUSIVE表示弹出包含该元素及以上的所有元素。这里说的弹出所有元素包含回退这些事务。
         popBackStack()是异步执行的，是丢到主线程的MessageQueue执行，popBackStackImmediate()是同步版本。
         */
    }

    @Override
    public void onStart() {//可见，不可以交互
        Log.i(ATG, "--->onStart");
        super.onStart();
    }

    @Override
    public void onResume() {//可见，可以交互
        Log.i(ATG, "--->onResume");
        super.onResume();

    }


    @Override
    public void onPause() {//可见，不可以交互
        Log.i(ATG, "--->onPause");
        super.onPause();
    }

    @Override
    public void onStop() {//不可见，不可以交互
        Log.i(ATG, "--->onStop");
        super.onStop();

    }


    @Override
    public void onDestroy() {
        Log.i(ATG, "--->onDestroy");
        super.onDestroy();

    }

    @Override
    protected void onRestart() {
        Log.i(ATG, "--->onRestart");
        super.onRestart();
    }
}
