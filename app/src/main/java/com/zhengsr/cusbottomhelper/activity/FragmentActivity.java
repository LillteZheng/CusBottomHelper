package com.zhengsr.cusbottomhelper.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhengsr.cusbottomhelper.R;
import com.zhengsr.cusbottomhelper.TestFragment;
import com.zhengsr.cusbottomlib.IBottomClickListener;
import com.zhengsr.cusbottomlib.view.CusBottomLayout;

public class FragmentActivity extends AppCompatActivity implements IBottomClickListener {

    private TestFragment mHomeFragment;
    private TestFragment mPersonFragment;
    private TestFragment mFindFragment;
    private TestFragment mSortFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        CusBottomLayout layout = findViewById(R.id.bottom_ly);
        layout.setBottomClickListener(this);
        mHomeFragment = TestFragment.getInstance("首页");

        getSupportFragmentManager().beginTransaction().add(R.id.cus_content_ly,mHomeFragment).commit();

    }

    @Override
    public void onClick(View view, int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        resetFragment(ft);
        switch (position){
            case 0:
                if (mHomeFragment == null){
                    mHomeFragment = TestFragment.getInstance("首页");
                    ft.add(R.id.cus_content_ly,mHomeFragment);
                }else{
                    ft.show(mHomeFragment);
                }
                break;
            case 1:
                if (mPersonFragment == null){
                    mPersonFragment = TestFragment.getInstance("通讯录");
                    ft.add(R.id.cus_content_ly,mPersonFragment);
                }else{
                    ft.show(mPersonFragment);
                }
                break;
            case 2:
                if (mFindFragment == null){
                    mFindFragment = TestFragment.getInstance("发现");
                    ft.add(R.id.cus_content_ly,mFindFragment);
                }else{
                    ft.show(mFindFragment);
                }
                break;
            case 3:
                if (mSortFragment == null){
                    mSortFragment = TestFragment.getInstance("分类");
                    ft.add(R.id.cus_content_ly,mSortFragment);
                }else{
                    ft.show(mSortFragment);
                }
                break;
            default:
                break;
        }
        ft.commit();
    }

    private void resetFragment(FragmentTransaction ft){
        if (mHomeFragment != null){
            ft.hide(mHomeFragment);
        }
        if (mPersonFragment != null){
            ft.hide(mPersonFragment);
        }
        if (mFindFragment != null){
            ft.hide(mFindFragment);
        }
        if (mSortFragment != null){
            ft.hide(mSortFragment);
        }
    }
}
