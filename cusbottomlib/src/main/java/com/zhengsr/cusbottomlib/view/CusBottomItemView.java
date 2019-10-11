package com.zhengsr.cusbottomlib.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhengsr.cusbottomlib.R;

/**
 * Created by zhengshaorui on 2018/5/1.
 */

public class CusBottomItemView extends LinearLayout {
    private static final String TAG = "CusBottomItem";
    private ImageView mImageView;
    private TextView mTextView;
    private int mNormalTextColor;
    private int mChooseTextColor;
    private boolean isNotChangeImg;
    private boolean mBothChange;

    public CusBottomItemView(Context context) {
        this(context,null);
    }

    public CusBottomItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CusBottomItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        removeAllViews();
        setClipChildren(false);
        //添加布局
        View view = LayoutInflater.from(context).inflate(R.layout.cus_bottom_item_layout,this,false);
        //自定义view属性
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.CusBottomItemView);
        mNormalTextColor = ta.getColor(R.styleable.CusBottomItemView_cus_normal_text_color,-1);
        mChooseTextColor = ta.getColor(R.styleable.CusBottomItemView_cus_selected_text_coclor,-1);
        String itemtext  = ta.getString(R.styleable.CusBottomItemView_cus_text);
        int textsize = ta.getDimensionPixelSize(R.styleable.CusBottomItemView_cus_text_size,12);
        int picSize = ta.getDimensionPixelSize(R.styleable.CusBottomItemView_cus_image_size,20);
        int picmargintop = ta.getDimensionPixelSize(R.styleable.CusBottomItemView_cus_image_margin_top,0);
        int textmargintop = ta.getDimensionPixelSize(R.styleable.CusBottomItemView_cus_text_margin_top,0);
        boolean isNoBakcground = ta.getBoolean(R.styleable.CusBottomItemView_cus_no_background,false);
        if (isNoBakcground){
            view.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
        boolean isChoose = ta.getBoolean(R.styleable.CusBottomItemView_cus_isChoose,false);
        isNotChangeImg =  ta.getBoolean(R.styleable.CusBottomItemView_cus_not_change_img,false);

        int iconNormal = ta.getResourceId(R.styleable.CusBottomItemView_cus_img_normal,-1);
        int iconSelect = ta.getResourceId(R.styleable.CusBottomItemView_cus_img_select,-1);

        mBothChange = ta.getBoolean(R.styleable.CusBottomItemView_cus_change_both,false);
        ta.recycle();



        /**
         * 配置View
         */
        mImageView = view.findViewById(R.id.cus_item_iv);
        mTextView = view.findViewById(R.id.cus_item_tv);
        mTextView.setText(itemtext);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);

        addView(view);

        /**
         * 配置View的参数
         */

        if (mBothChange){
            setItemStatus(isChoose);
            mImageView.setImageResource(iconNormal);
        }else{

            setImageStatus(mImageView,iconNormal,iconSelect);
            setTextStatus(mTextView, mNormalTextColor, mChooseTextColor);
            if (isChoose){
                mImageView.setSelected(true);
                mTextView.setSelected(true);
            }
        }

        ViewGroup.LayoutParams params =  mImageView.getLayoutParams();
        params.width = picSize;
        params.height = picSize;
        mImageView.setLayoutParams(params);

        //父布局，然后改子空间的 margin
        setMargins(mImageView,0,picmargintop,0,0);
        setMargins(mTextView,0,textmargintop,0,0);
    }


    public boolean isBochChange(){
        return mBothChange;
    }

    /**
     * 设置image
     * @param view
     * @param normalres
     * @param selectres
     */
    private void setImageStatus(ImageView view, int normalres, int selectres) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (normalres != -1 && selectres != -1) {
            Drawable select = getContext().getResources().getDrawable(selectres);
            Drawable normal = getContext().getResources().getDrawable(normalres);
            stateListDrawable.addState(new int[]{android.R.attr.state_selected}, select);
            //负号代表 false
            stateListDrawable.addState(new int[]{-android.R.attr.state_selected}, normal);

            view.setBackground(stateListDrawable);
        }else{
            view.setImageResource(normalres);
        }

    }


    /**
     * 设置文字状态
     */
    private void setTextStatus(TextView view, int normalres, int selectres) {

        if (normalres != 0 && selectres != 0) {
            int[]  colors = new int[]{normalres,selectres};
            int[][] states = new int[2][];
            states[0] = new int[]{-android.R.attr.state_selected};
            states[1] = new int[]{android.R.attr.state_selected};
            ColorStateList colorStateList = new ColorStateList(states,colors);
            view.setTextColor(colorStateList);
        }else{
            view.setTextColor(normalres);
        }

    }


    public void setSelect(boolean isSelect){
        if (isSelect){
            mImageView.setSelected(true);
            mTextView.setSelected(true);
        }else{
            mImageView.setSelected(false);
            mTextView.setSelected(false);
        }
    }

    /**
     * 统一改变图片和文字的颜色，适合统一管理
     * @param isChoose
     */
    public void setItemStatus(boolean isChoose){
        if (isChoose) {
            mTextView.setTextColor(mChooseTextColor);
            if (!isNotChangeImg) {
                mImageView.clearColorFilter();
                mImageView.setColorFilter(mChooseTextColor);
            }
        } else {
            mTextView.setTextColor(mNormalTextColor);
            if (!isNotChangeImg) {
                mImageView.clearColorFilter();
                mImageView.setColorFilter(mNormalTextColor);
            }
        }
    }

    /**
     * 设置偏移量
     */
    public void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.setLayoutParams(p);
        }
    }
}
