package com.zhengsr.cusbottomlib.util;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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
     * @param fragments
     */
    public void hideAllFragment(List<Fragment> fragments){
        FragmentTransaction ft =  mFragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            ft.hide(fragment);
        }
        ft.commit();
    }

    /**
     * 添加或者显示fragment
     * @param fragment
     */
    public void addOrShowFragment(Fragment fragment){
        FragmentTransaction ft =  mFragmentManager.beginTransaction();
        if (fragment == null){
            ft.add(mContentId,fragment);
        }else{
            if (!fragment.isAdded()){
                ft.add(mContentId,fragment);
            }
            ft.show(fragment);

        }
        ft.commit();
    }

    /**
     * 添加或者显示fragment
     * @param fragment
     * @param tag
     */
    public  void addOrShowFragment(Fragment fragment,String tag){
        FragmentTransaction ft =  mFragmentManager.beginTransaction();
        if (fragment == null){
            ft.add(mContentId,fragment,tag);
        }else{
            if (!fragment.isAdded()){
                ft.add(mContentId,fragment);
            }
            ft.show(fragment);
        }
        ft.commit();
    }

    /**
     * 移除fragment
     * @param fragment
     */
    public void removeFragment(Fragment fragment){
        mFragmentManager.beginTransaction().remove(fragment).commit();
    }

    /**
     * 替换fragment
     * @param fragment
     */
    public void replaceFragment(Fragment fragment){
        mFragmentManager.beginTransaction().replace(mContentId,fragment).commit();
    }

    /**
     * 替换fragment
     * @param fragment
     * @param tag
     */
    public void replaceFragment(Fragment fragment,String tag){
        mFragmentManager.beginTransaction().replace(mContentId,fragment,tag).commit();
    }
}
