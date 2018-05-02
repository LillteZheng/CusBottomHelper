package com.zhengsr.cusbottomhelper;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhengsr.cusbottomhelper.activity.FragmentActivity;
import com.zhengsr.cusbottomhelper.activity.ViewPagerActivity;

public class MainActivity extends AppCompatActivity  {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onfragment(View view) {
        startActivity(new Intent(this,FragmentActivity.class));
    }
    public void onviewpager(View view) {
        startActivity(new Intent(this,ViewPagerActivity.class));
    }
}
