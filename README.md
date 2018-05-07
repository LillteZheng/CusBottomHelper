

## 效果图：
![image](https://github.com/LillteZheng/ViewPagerHelper/raw/master/gif/cus_fragment.gif)

![image](https://github.com/LillteZheng/ViewPagerHelper/raw/master/gif/cus_viewpager.gif)

## 使用
在你的工程上添加：
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

在你需要的地方添加：
```
implementation 'com.github.LillteZheng:CusBottomHelper:v0.1'
```

## 配置

xml 布局配置
```
<android.support.v7.widget.CardView
        android:layout_width="match_parent"
        android:layout_alignParentBottom="true"
        android:layout_height="45dp">

        <com.zhengsr.cusbottomlib.view.CusBottomLayout
            android:id="@+id/bottom_ly"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            >
            <com.zhengsr.cusbottomlib.view.CusBottomItem
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1"
                app:cus_image_margin_top="4dp"
                app:cus_image_size="25dp"
                app:cus_normal_pic="@mipmap/home"
                app:cus_selected_pic="@mipmap/home_focus"
                app:cus_normal_text_color="#8a8a8a"
                app:cus_selected_text_coclor="#1296db"
                app:cus_text="首页"
                app:cus_text_margin_top="2dp"
                app:cus_text_size="11sp" />
            ....
        </com.zhengsr.cusbottomlib.view.CusBottomLayout>
    </android.support.v7.widget.CardView>
```

java 代码中这样配置：
```
CusBottomLayout layout = (CusBottomLayout)findViewById(R.id.bottom_ly);
layout.setBottomClickListener(new IBottomClickListener() {
    @Override
    public void onClick(View view, int position) {
        //TODO 根据 position 换fragment 或者 viewpager
    }
});
```
如果需要使用 viewpager ，则把 viewpager 添加进来即可：
```
layout.addViewPager(mViewPager);
```
