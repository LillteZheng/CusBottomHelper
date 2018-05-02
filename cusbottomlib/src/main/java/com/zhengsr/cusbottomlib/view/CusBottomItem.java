package com.zhengsr.cusbottomlib.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhengsr.cusbottomlib.R;

/**
 * Created by zhengshaorui on 2018/5/1.
 */

public class CusBottomItem extends LinearLayout {
    private static final String TAG = "CusBottomItem";
    /**
     * attrs
     */
    private int mNormalPicResid = 0;
    private int mNormalTextColor = 0;
    private int mPicSize = 20;
    public CusBottomItem(Context context) {
        this(context,null);
    }

    public CusBottomItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CusBottomItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        removeAllViews();
        View view = LayoutInflater.from(context).inflate(R.layout.cus_bottom_item_layout,this,false);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.CusBottomItem);
        int ivnormalResid = ta.getResourceId(R.styleable.CusBottomItem_cus_normal_pic,-1);
        int ivfocusPicResid = ta.getResourceId(R.styleable.CusBottomItem_cus_selected_pic,-1);
        int tvNormalColor = ta.getColor(R.styleable.CusBottomItem_cus_normal_text_color,-1);
        int tvSelectColor = ta.getColor(R.styleable.CusBottomItem_cus_selected_text_coclor,-1);
        String itemtext  = ta.getString(R.styleable.CusBottomItem_cus_text);
        int textsize = ta.getDimensionPixelSize(R.styleable.CusBottomItem_cus_text_size,12);
        mPicSize = ta.getDimensionPixelSize(R.styleable.CusBottomItem_cus_image_size,mPicSize);
        int picmargintop = ta.getDimensionPixelSize(R.styleable.CusBottomItem_cus_image_margin_top,0);
        int textmargintop = ta.getDimensionPixelSize(R.styleable.CusBottomItem_cus_text_margin_top,0);

        FrameLayout frameLayout = view.findViewById(R.id.cus_item_content);
        ImageView imageView = view.findViewById(R.id.cus_item_iv);
        TextView textView = view.findViewById(R.id.cus_item_tv);
        setImageStatus(imageView,ivnormalResid,ivfocusPicResid);
        setTextStatus(textView,tvNormalColor,tvSelectColor);
        if (mNormalPicResid != 0){
            imageView.setImageResource(mNormalPicResid);
        }

        if (mNormalTextColor != 0){
            textView.setTextColor(mNormalTextColor);
        }
        textView.setText(itemtext);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textsize);
        ta.recycle();
        addView(view);

        //pic size
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) imageView.getLayoutParams();
        params.width = mPicSize;
        params.height = mPicSize;
        imageView.setLayoutParams(params);

        //父布局，然后改子空间的 margin
        setMargins(frameLayout,0,picmargintop,0,0);
        setMargins(textView,0,textmargintop,0,0);

    }


    /**
     * 设置图片的选择状态
     * @param view
     * @param normalres
     * @param selectres
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setImageStatus(ImageView view, int normalres, int selectres) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (normalres != 0 && selectres != 0) {
            Drawable select = getContext().getResources().getDrawable(selectres);
            Drawable normal = getContext().getResources().getDrawable(normalres);
            stateListDrawable.addState(new int[]{android.R.attr.state_selected}, select);
            //负号代表 false
            stateListDrawable.addState(new int[]{-android.R.attr.state_selected}, normal);

            view.setBackground(stateListDrawable);
        }else{
            throw new IllegalStateException("you should set cus_normal_pic and cus_selected_pic " +
                    "in your root layout");
        }

    }

    /**
     * 设置图片的选择状态
     * @param view
     * @param normalres
     * @param selectres
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
            throw new IllegalStateException("you should set cus_normal_text_color and cus_select_text_color " +
                    "in your root layout");
        }

    }

    public  void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

}
