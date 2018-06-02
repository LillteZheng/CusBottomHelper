package com.zhengsr.cusbottomlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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

public class CusBottomClipView extends LinearLayout {
    private static final String TAG = "CusBottomItem";
    private static final int SIZE_DEFAULT = -1;
    /**
     * attrs
     */
    public CusBottomClipView(Context context) {
        this(context,null);
    }

    public CusBottomClipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CusBottomClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        removeAllViews();
        setClipChildren(false);
        View view = LayoutInflater.from(context).inflate(R.layout.cus_bottom_clip_item_layout,this,false);


        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.CusBottomClipView);

        //Attrs textview
        int textSize = ta.getDimensionPixelSize(R.styleable.CusBottomItem_cus_text_size,12);
        String text = ta.getString(R.styleable.CusBottomClipView_clip_text);
        int textColor = ta.getColor(R.styleable.CusBottomClipView_clip_text_color, Color.parseColor("#323232"));
        int textmargintop = ta.getDimensionPixelSize(R.styleable.CusBottomItem_cus_text_margin_top,0);

        //atts iamge
        int imageSize = ta.getDimensionPixelSize(R.styleable.CusBottomClipView_clip_image_size,50);
        int drawableRes = ta.getResourceId(R.styleable.CusBottomClipView_clip_drawable,-1);

        LinearLayout clipLy = view.findViewById(R.id.cus_bottom_clip_ly);
        ImageView imageView = view.findViewById(R.id.cus_bottom_clip_iv);
        TextView textView = view.findViewById(R.id.cus_bottom_clip_tv);


        //textview
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        textView.setTextColor(textColor);
        //imageview
        imageView.setBackgroundResource(drawableRes);


        addView(view);

        //pic size
        setLayoutParams(imageView,imageSize,imageSize);
        //textview
        setMargins(textView,0,textmargintop,0,0);





    }


    private void setLayoutParams(View view,@Nullable int width, int height){
        ViewGroup.LayoutParams params = view.getLayoutParams();

        params.width = width;

        params.height = height;
        view.setLayoutParams(params);
    }



    public  void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams p = (MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

}
