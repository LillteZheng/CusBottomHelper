package com.zhengsr.cusbottomlib.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhengsr.cusbottomlib.IBottomClickListener;
import com.zhengsr.cusbottomlib.R;

/**
 * Created by zhengshaorui on 2018/5/1.
 */

public class CusBottomLayout extends LinearLayout {
    private static final String TAG = "CusBottomLayout";
    private IBottomClickListener mIBottomClickListener;
    public CusBottomLayout(Context context) {
        this(context,null);
    }

    public CusBottomLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CusBottomLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        resetAndSelet(0);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            final int finalId = i;
            View contentView = view.findViewById(R.id.cus_bottom_item_ly);
            if (contentView != null) {
                contentView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "zsr --> onClick: " + finalId);
                        resetAndSelet(finalId);
                        if (mIBottomClickListener != null) {
                            mIBottomClickListener.onClick(v, finalId);
                        }
                    }
                });
            }

        }
    }

    public void setBottomClickListener(IBottomClickListener listener){
        mIBottomClickListener = listener;
    }

    /**
     * 先还原，再选中
     */
    private void resetAndSelet(int position){
        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view instanceof CusBottomItemView) {
                ImageView imageView = view.findViewById(R.id.cus_item_iv);
                TextView textView = view.findViewById(R.id.cus_item_tv);

                imageView.setSelected(false);
                textView.setSelected(false);
            }
        }


        View view = getChildAt(position);
        ImageView imageView = view.findViewById(R.id.cus_item_iv);
        TextView textView = view.findViewById(R.id.cus_item_tv);

        imageView.setSelected(true);
        textView.setSelected(true);
    }

    public void addViewPager(ViewPager viewPager){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetAndSelet(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
