package com.zhengsr.cusbottomlib.view;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.MenuRes;
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
import com.zhengsr.cusbottomlib.SavedState;

/**
 * Created by zhengshaorui on 2018/5/1.
 */

public class CusBottomLayout extends LinearLayout {
    private static final String TAG = "CusBottomLayout";
    private IBottomClickListener mIBottomClickListener;
    private  int mPreIndex;
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
        //默认不要选中第一个
      //  resetAndSelet(0);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            final int finalId = i;
            View contentView = view.findViewById(R.id.cus_bottom_item_ly);
            if (contentView != null) {
                contentView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetAndSelet(finalId);
                        if (mIBottomClickListener != null) {
                            mIBottomClickListener.onBottomClick(v, finalId,mPreIndex);
                            mPreIndex = finalId;
                        }
                    }
                });
            }

        }
    }

    public void setBottomClickListener(IBottomClickListener listener){
        mIBottomClickListener = listener;
    }


    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(state);
        mPreIndex = ss.preIndex;
        resetAndSelet(mPreIndex);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable = super.onSaveInstanceState();
        SavedState ss = new SavedState(parcelable);
        ss.preIndex = mPreIndex;
        return ss;

    }

    /**
     * 先还原，再选中
     */
    public void resetAndSelet(int position){
        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            CusBottomItemView view = (CusBottomItemView) getChildAt(i);
            if (view.isBochChange()) {
                view.setItemStatus(false);
            }else{
                view.setSelect(false);
            }

        }
        CusBottomItemView view = (CusBottomItemView) getChildAt(position);
        if (view.isBochChange()) {
            view.setItemStatus(true);
        }else{
            view.setSelect(true);
        }
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
