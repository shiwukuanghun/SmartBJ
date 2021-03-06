package com.itheima.smartbj.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.itheima.smartbj.R;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HuangBin on 2017/3/18 13:56.
 */

public class NewsPage extends RelativeLayout {

    private static final String[] CONTENT = new String[]{"Recent", "Artists", "Albums", "Songs", "Playlists", "Genres", "Military", "Sports", "History", "Science"};
    @BindView(R.id.indicator)
    TabPageIndicator mIndicator;
    @BindView(R.id.pager)
    ViewPager mPager;

    public NewsPage(Context context) {
        this(context, null);
    }

    public NewsPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_news_page, this);
        ButterKnife.bind(this, this);
        mPager.setAdapter(mPagerAdapter);
        mIndicator.setViewPager(mPager);
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return CONTENT.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            /*TextView textView = new TextView(getContext());
            textView.setText(CONTENT[position]);
            container.addView(textView);*/
            NewsPullToRefreshListView newsPullToRefreshListView = new NewsPullToRefreshListView(getContext());
            container.addView(newsPullToRefreshListView);
            return newsPullToRefreshListView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position];
        }
    };
}
