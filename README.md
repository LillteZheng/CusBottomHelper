

## 效果图：
![image](https://user-gold-cdn.xitu.io/2018/6/4/163cb1d02115b2ca?w=329&h=595&f=gif&s=87515)

![image](https://user-gold-cdn.xitu.io/2018/6/4/163cb1d020f011c6?w=324&h=567&f=gif&s=120947)

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
implementation 'com.github.LillteZheng:CusBottomHelper:v0.2'
```

## XML 配置

配置非常简单，只有两个view，一个是CusBottomItemView,这个是主要view，用来配置 image 和 text的， 还有一个是CusBottomLayout，这个用来包裹 CusBottomItemView，主要用于配置点击事件等等。代码如下：

```


    <com.zhengsr.cusbottomlib.view.CusBottomLayout
        android:id="@+id/bottom_ly"
        android:layout_width="match_parent"
        android:layout_height="45dp"
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

        <!--这个用来配置底部突出的view，高度要高于父布局-->
        <com.zhengsr.cusbottomlib.view.CusBottomItemView
            android:layout_width="0dp"
            android:layout_height="60dp"
            android:layout_weight="1"
            app:cus_image_size="45dp"
            app:cus_normal_pic="@drawable/shape_bg"
            app:cus_normal_text_color="#8a8a8a"
            app:cus_selected_text_coclor="#1296db"
            app:cus_no_background="true"
            app:cus_text="活动"
            app:cus_text_margin_top="2dp"
            app:cus_text_size="11sp" />
        ....
    </com.zhengsr.cusbottomlib.view.CusBottomLayout>

```
上面也使用了比较流行的地步突出，主要设置高度，然后在你的父布局加上 **android:clipChildren="false"**


**java 代码中这样配置**
```
CusBottomLayout layout = (CusBottomLayout)findViewById(R.id.bottom_ly);
layout.setBottomClickListener(new IBottomClickListener() {
    @Override
    public void onClick(View view, int position) {
        //TODO 根据 position 换fragment 或者 viewpager
    }
});
```
而在使用fragment中，CusbottomHelper，也提供了一个简单的fragment工具:
```
//实例 CusBFragmentUtil,主要是 fragmentManager和 framelayout的id
mCusBFragmentUtil = CusBFragmentUtil.create(getSupportFragmentManager(), R.id.content_ly);
//配置第一个fragment
mCusBFragmentUtil.loadRootFragment(mHomeFragment);
```
然后上面，我们就可以这样写啦：
```
@Override
    public void onClick(View view, int position) {
        mCusBFragmentUtil.hideAllFragment(mFragments);
        switch (position){
            case 0:
                mCusBFragmentUtil.addOrShowFragment(mHomeFragment);
                break;
            case 1:
                mCusBFragmentUtil.addOrShowFragment(mPersonFragment);
                break;
            case 2:
                mCusBFragmentUtil.addOrShowFragment(mGiftFragment);
                break;
            case 3:
                mCusBFragmentUtil.addOrShowFragment(mFindFragment);
                break;
            case 4:
                mCusBFragmentUtil.addOrShowFragment(mSortFragment);
                break;
            default:
                break;
        }

    }
```


如果使用 **viewpager**，则把 viewpager 添加进来即可：
```
layout.addViewPager(mViewPager);
```
