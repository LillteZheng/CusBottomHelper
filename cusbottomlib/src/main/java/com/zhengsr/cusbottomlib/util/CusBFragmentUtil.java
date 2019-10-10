package com.zhengsr.cusbottomlib.util;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.util.List;

/**
 * Created by zhengshaorui on 2018/6/2.
 */

public class CusBFragmentUtil {
    private static final String TAG = "CusBFragmentUtil";
    private FragmentManager mFragmentManager;
    private int mContentId;
    public static CusBFragmentUtil create(FragmentManager fm,@IdRes int contentId){
        return new CusBFragmentUtil(fm,contentId);
    }

    private CusBFragmentUtil(FragmentManager fm,@IdRes int contentId){
        mFragmentManager = fm;
        mContentId = contentId;
    }

    /**
     * 加载第一个fragment
     * @param fragment
     */
    public void loadRootFragment(Fragment fragment){
        addOrShowFragment(fragment);
    }

    /**
     * 加载第一个fragment
     * @param fragment
     * @param tag
     */
    public void loadRootFragment(Fragment fragment,String tag){
        mFragmentManager.beginTransaction().add(mContentId,fragment,tag).commit();
    }


    /**
     * 隐藏所有的fragment
     */
    public void hideAllFragment(){
        List<Fragment> fragments = mFragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null){
                FragmentTransaction ft =  mFragmentManager.beginTransaction();
                ft.hide(fragment);
                ft.commit();
            }

        }

    }

    /**
     * 添加或者显示fragment
     * @param fragment
     */
    public void addOrShowFragment(Fragment fragment){
        if (fragment != null){
            FragmentTransaction ft =  mFragmentManager.beginTransaction();
            if (!fragment.isAdded()){
                ft.add(mContentId,fragment);
            }
            ft.show(fragment);
            ft.commit();
        }


    }

    /**
     * 添加或者显示fragment
     * @param fragment
     * @param tag
     */
    public  void addOrShowFragment(Fragment fragment,String tag){
        if (fragment != null){
            FragmentTransaction ft =  mFragmentManager.beginTransaction();
            if (!fragment.isAdded()){
                ft.add(mContentId,fragment,tag);
            }
            ft.show(fragment);
            ft.commit();
        }

    }

    /**
     * 移除fragment
     * @param fragment
     */
    public void removeFragment(Fragment fragment){
        if (fragment != null) {
            mFragmentManager.beginTransaction().remove(fragment).commit();
        }
    }

    /**
     * 替换fragment
     * @param fragment
     */
    public void replaceFragment(Fragment fragment){
        if (fragment != null) {
            mFragmentManager.beginTransaction().replace(mContentId, fragment).commit();
        }
    }

    /**
     * 替换fragment
     * @param fragment
     * @param tag
     */
    public void replaceFragment(Fragment fragment,String tag){
        if (fragment != null) {
            mFragmentManager.beginTransaction().replace(mContentId, fragment, tag).commit();
        }
    }
}
