package com.example.lyf.androidlyfcode.fragment;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/9.
 */

public class FragmentMagerImpl extends FragmentMager {


    /**
     * 先从mAdded中查找是否有该Fragment，如果没找到，再从mActive中查找是否有该Fragment。
     * mAdded是已经添加到Activity的Fragment的集合，mActive不仅包含mAdded，还包含虽然不在Activity中，但还在回退栈中的Fragment。
     */
    ArrayList<Fragment> mActive;//mActive不仅包含mAdded，还包含虽然不在Activity中，但还在回退栈中的Fragment。,所有的
    ArrayList<Fragment> mAdded;//mAdded是已经添加到Activity的Fragment的集合  //在Activciy的

    @Override
    public void findFragmentByTag(String tag) {
        if (mAdded != null && tag != null) {
            //9                                8,7,6,5,4,3,2,1,0
            for (int i = mAdded.size() - 1; i >= 0; i--) {
                Fragment f = mAdded.get(i);
//                if (f != null && tag.equals(f.mTag)) {
//                    return f;
//                }
            }
        }
        if (mActive != null && tag != null) {
            for (int i = mActive.size() - 1; i >= 0; i--) {
                Fragment f = mActive.get(i);
//                if (f != null && tag.equals(f.mTag)) {
//                    return f;
//                }
            }
        }
//        return null;

    }
}
