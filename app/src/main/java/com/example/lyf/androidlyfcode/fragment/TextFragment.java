package com.example.lyf.androidlyfcode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lyf.androidlyfcode.R;

/**
 * Created by Administrator on 2018/2/9.
 */

public class TextFragment extends Fragment {

    FragmentActivity fragmentActivity;
    public final static String ATG = "Fragment";

    public static TextFragment newInstance(String str) {
        TextFragment frag = new TextFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ARGE", str);
        frag.setArguments(bundle);
        return frag;
    }


    public TextFragment() {
        Log.i(ATG, "--->conformation");
    }

    /**
     * onAttach()：Fragment和Activity相关联时调用。可以通过该方法获取Activity引用，还可以通过getArguments()获取参数。
     * onCreate()：Fragment被创建时调用。
     * onCreateView()：创建Fragment的布局。
     * onActivityCreated()：当Activity完成onCreate()时调用。
     *
     * @param context
     */

    @Override
    public void onAttach(Context context) {
        Log.i(ATG, "--->onAttach");
        super.onAttach(context);
        //ragment和Activity相关联时调用。可以通过该方法获取Activity引用，还可以通过getArguments()获取参数。
        Bundle bundle = getArguments();
        String str = bundle.getString("ARGE");
        fragmentActivity = (FragmentActivity) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(ATG, "--->onCreate");
        super.onCreate(savedInstanceState);
    }

    /**
     * 02-09 14:14:41.967 10163-10163/com.example.lyf.androidlyfcode I/Activity: --->onCreate
     * 02-09 14:14:41.967 10163-10163/com.example.lyf.androidlyfcode I/Activity: --->onStart
     * 02-09 14:14:41.969 10163-10163/com.example.lyf.androidlyfcode I/Fragment: --->onAttach
     * 02-09 14:14:41.969 10163-10163/com.example.lyf.androidlyfcode I/Fragment: --->onCreate
     * 02-09 14:14:41.970 10163-10163/com.example.lyf.androidlyfcode I/Fragment: --->onCreateView
     * 02-09 14:14:41.971 10163-10163/com.example.lyf.androidlyfcode I/Fragment: --->onActivityCreated
     * 02-09 14:14:41.971 10163-10163/com.example.lyf.androidlyfcode I/Fragment: --->onStart
     * 02-09 14:14:41.973 10163-10163/com.example.lyf.androidlyfcode I/Activity: --->onResume
     * 02-09 14:14:41.973 10163-10163/com.example.lyf.androidlyfcode I/Fragment: --->onResume
     * <p>
     * Fragment的onAttach()->onCreate()->onCreateView()->onActivityCreated()->onStart()
     * 都是在Activity的onStart()中调用的。
     * <p>
     * Fragment的onResume()在Activity的onResume()之后调用。
     * 因为，Frgment  attach activity 只有当Activity创建完成，可见时候，才创建Fragment,当Fragment可交互的时候，Fragment才可以交互
     * 举例子：一座大商场，由很多小店铺组成，只有当这个大商家建筑完成时候，小店铺才可以进行买卖，
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(ATG, "--->onCreateView");
        View view = LayoutInflater.from(fragmentActivity).inflate(R.layout.frament_ary, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(ATG, "--->onActivityCreated");
    }

    @Override
    public void onStart() {
        Log.i(ATG, "--->onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(ATG, "--->onResume");
    }


    /**
     * 02-09 14:20:03.013 10163-10163/com.example.lyf.androidlyfcode I/Activity: --->onPause
     * 02-09 14:20:03.014 10163-10163/com.example.lyf.androidlyfcode I/Fragment: --->onPause
     * 02-09 14:20:03.334 10163-10163/com.example.lyf.androidlyfcode I/Activity: --->onStop
     * 02-09 14:20:03.334 10163-10163/com.example.lyf.androidlyfcode I/Fragment: --->onStop
     * 02-09 14:20:03.334 10163-10163/com.example.lyf.androidlyfcode I/Activity: --->onDestroy
     * 02-09 14:20:03.334 10163-10163/com.example.lyf.androidlyfcode I/Fragment: --->onDestroyView
     * 02-09 14:20:03.335 10163-10163/com.example.lyf.androidlyfcode I/Fragment: --->onDestroy
     * 02-09 14:20:03.335 10163-10163/com.example.lyf.androidlyfcode I/Fragment: --->onDetach
     * <p>
     * 举例子：由于特发状况，地震，或者其他，商城大楼崩塌，从外观看，里面的小店铺是看不见的，
     * 大楼销毁时候，店铺也自然而然销毁，
     */

    @Override
    public void onPause() {//暂停
        super.onPause();
        Log.i(ATG, "--->onPause");
    }

    @Override
    public void onStop() {//停止
        super.onStop();
        Log.i(ATG, "--->onStop");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(ATG, "--->onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(ATG, "--->onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(ATG, "--->onDetach");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        /**
         * onAttach()之前，调用setUserVisibleHint(false)。
         onCreateView()之前，如果该界面为当前页，则调用setUserVisibleHint(true)，否则调用setUserVisibleHint(false)。
         界面变为可见时，调用setUserVisibleHint(true)。
         界面变为不可见时，调用setUserVisibleHint(false)。
         */
    }
}
