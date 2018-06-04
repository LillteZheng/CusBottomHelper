package com.zhengsr.cusbottomhelper.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhengsr.cusbottomhelper.R;
import com.zhengsr.cusbottomhelper.TestFragment;
import com.zhengsr.cusbottomlib.IBottomClickListener;
import com.zhengsr.cusbottomlib.util.CusBFragmentUtil;
import com.zhengsr.cusbottomlib.view.CusBottomLayout;

public class FragmentActivity extends AppCompatActivity implements IBottomClickListener {

    private TestFragment mHomeFragment;
    private TestFragment mPersonFragment;
    private TestFragment mGiftFragment;
    private TestFragment mFindFragment;
    private TestFragment mSortFragment;

    private CusBFragmentUtil mCusBFragmentUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        CusBottomLayout layout = findViewById(R.id.bottom_ly);
        layout.setBottomClickListener(this);
        mHomeFragment = TestFragment.getInstance("首页");


        //实例 CusBFragmentUtil,主要是 fragmentManager和 framelayout的id
        mCusBFragmentUtil = CusBFragmentUtil.create(getSupportFragmentManager(), R.id.content_ly);
        //配置第一个fragment
        mCusBFragmentUtil.loadRootFragment(mHomeFragment);

    }

    @Override
    public void onClick(View view, int position) {
        mCusBFragmentUtil.hideAllFragment();
        switch (position){
            case 0:
                if (mHomeFragment == null){
                    mHomeFragment = TestFragment.getInstance("首页");

                }
                mCusBFragmentUtil.addOrShowFragment(mHomeFragment);
                break;
            case 1:
                if (mSortFragment == null){
                    mPersonFragment = TestFragment.getInstance("通讯录");
                }
                mCusBFragmentUtil.addOrShowFragment(mPersonFragment);
                break;
            case 2:
                if (mGiftFragment == null){
                    mGiftFragment = TestFragment.getInstance("活动");
                }
                mCusBFragmentUtil.addOrShowFragment(mGiftFragment);
                break;
            case 3:
                if (mFindFragment == null){
                    mFindFragment = TestFragment.getInstance("发现");
                }
                mCusBFragmentUtil.addOrShowFragment(mFindFragment);
                break;
            case 4:
                if (mSortFragment == null){
                    mSortFragment = TestFragment.getInstance("分类");
                }
                mCusBFragmentUtil.addOrShowFragment(mSortFragment);
                break;
            default:
                break;
        }

    }


}
