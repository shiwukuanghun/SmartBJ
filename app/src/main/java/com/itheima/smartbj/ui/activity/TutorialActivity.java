package com.itheima.smartbj.ui.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.itheima.smartbj.R;
import com.itheima.smartbj.utils.SPUtils;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by HuangBin on 2017/3/16 20:20.
 */

public class TutorialActivity extends BaseActivity {

    @BindView(R.id.start)
    Button mStart;
    @BindView(R.id.indicator)
    CirclePageIndicator mIndicator;

    private int[] mImages = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_tutorial;
    }

    @Override
    protected void init() {
        //初始化viewPager
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        //关联viewPager
        mIndicator.setViewPager(mViewPager);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == mImages.length - 1) {
                mStart.setVisibility(View.VISIBLE);
            } else {
                mStart.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(TutorialActivity.this);
            imageView.setImageResource(mImages[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//拉伸X,Y直到填满整个imageVIew
            container.addView(imageView);
            return imageView;  //返回对应页面的标记
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object); //将要销毁的页面移除
        }
    };

    //点击立即体验进入主页
    @OnClick(R.id.start)
    public void onClick() {
        /*Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();*/
        navigateTo(MainActivity.class);
        SPUtils.saveBoolean(this, "started", true);
    }
}
