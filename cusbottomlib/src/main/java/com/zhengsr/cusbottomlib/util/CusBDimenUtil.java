package com.zhengsr.cusbottomlib.util;

import android.content.Context;

/**
 * Created by zhengshaorui on 2018/5/1.
 */

public class CusBDimenUtil {

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
