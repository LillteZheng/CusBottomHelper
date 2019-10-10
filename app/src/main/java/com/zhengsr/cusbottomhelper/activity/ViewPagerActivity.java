package com.zhengsr.cusbottomhelper.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhengsr.cusbottomhelper.R;
import com.zhengsr.cusbottomhelper.TestFragment;
import com.zhengsr.cusbottomlib.IBottomClickListener;
import com.zhengsr.cusbottomlib.view.CusBottomLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity implements IBottomClickListener {
    private ViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<>();
    private CusBottomLayout mCusBottomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mViewPager = findViewById(R.id.cus_viewpager);
        mCusBottomLayout = (CusBottomLayout)findViewById(R.id.bottom_ly);
        mCusBottomLayout.setBottomClickListener(this);
        initData();
    }

    private void initData() {

        mFragments.add(TestFragment.getInstance("首页"));
        mFragments.add(TestFragment.getInstance("通讯录"));
        mFragments.add(TestFragment.getInstance("发现"));
        mFragments.add(TestFragment.getInstance("分类"));

        mViewPager.setAdapter(new cusPagerAdapter(getSupportFragmentManager()));

        mCusBottomLayout.addViewPager(mViewPager);
    }



    @Override
    public void onBottomClick(View view, int curPosition, int prePosition) {
        mViewPager.setCurrentItem(curPosition);
    }


    class cusPagerAdapter extends FragmentPagerAdapter{

        public cusPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
