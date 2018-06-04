package com.zhengsr.cusbottomhelper.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhengsr.cusbottomhelper.R;
import com.zhengsr.cusbottomhelper.TestFragment;
import com.zhengsr.cusbottomlib.IBottomClickListener;
import com.zhengsr.cusbottomlib.util.CusBFragmentUtil;
import com.zhengsr.cusbottomlib.view.CusBottomLayout;

import java.util.ArrayList;
import java.util.List;

public class FragmentActivity extends AppCompatActivity implements IBottomClickListener {

    private TestFragment mHomeFragment;
    private TestFragment mPersonFragment;
    private TestFragment mGiftFragment;
    private TestFragment mFindFragment;
    private TestFragment mSortFragment;

    private List<Fragment> mFragments = new ArrayList<>();
    private CusBFragmentUtil mCusBFragmentUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        CusBottomLayout layout = findViewById(R.id.bottom_ly);
        layout.setBottomClickListener(this);
        mHomeFragment = TestFragment.getInstance("首页");
        mPersonFragment = TestFragment.getInstance("通讯录");
        mGiftFragment = TestFragment.getInstance("活动");
        mFindFragment = TestFragment.getInstance("发现");
        mSortFragment = TestFragment.getInstance("分类");

        //实例 CusBFragmentUtil,主要是 fragmentManager和 framelayout的id
        mCusBFragmentUtil = CusBFragmentUtil.create(getSupportFragmentManager(), R.id.content_ly);
        //配置第一个fragment
        mCusBFragmentUtil.loadRootFragment(mHomeFragment);
        mFragments.add(mHomeFragment);
        mFragments.add(mPersonFragment);
        mFragments.add(mGiftFragment);
        mFragments.add(mFindFragment);
        mFragments.add(mSortFragment);
    }

    @Override
    public void onClick(View view, int position) {
        mCusBFragmentUtil.hideAllFragment(mFragments);
        switch (position){
            case 0:
                mCusBFragmentUtil.addOrShowFragment(mHomeFragment);
                break;
            case 1:
                mCusBFragmentUtil.addOrShowFragment(mPersonFragment);
                break;
            case 2:
                mCusBFragmentUtil.addOrShowFragment(mGiftFragment);
                break;
            case 3:
                mCusBFragmentUtil.addOrShowFragment(mFindFragment);
                break;
            case 4:
                mCusBFragmentUtil.addOrShowFragment(mSortFragment);
                break;
            default:
                break;
        }

    }


}
