package com.zhengsr.cusbottomhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhengshaorui on 2018/5/3.
 */

public class TestFragment extends Fragment {
    public static final String CUSKEY = "cuskey";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        Bundle bundle = getArguments();
        if (bundle != null){
            String title = bundle.getString(CUSKEY);
            textView.setText(title);
        }

        return textView;
    }

    public static TestFragment getInstance(String key){
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CUSKEY,key);
        fragment.setArguments(bundle);
        return fragment;
    }
}
