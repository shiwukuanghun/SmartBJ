package com.itheima.smartbj.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.smartbj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HuangBin on 2017/3/17 19:33.
 */

public class TagPage extends RelativeLayout {
    @BindView(R.id.menu)
    ImageView mMenu;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.tab_page_content_frame)
    FrameLayout mTabPageContentFrame;

    private OnTabPageChangeListener mOnTabPageChangeListener;

    public TagPage(Context context) {
        this(context, null);
    }

    public TagPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_tab_page, this);
        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.menu)
    public void onClick() {
        //通知外界发生了菜单按钮的点击事件
        if (mOnTabPageChangeListener != null) {
            mOnTabPageChangeListener.onTabPageMenuClick();
        }
    }

    public void hideMenu() {
        mMenu.setVisibility(View.GONE);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void OnMenuSwitch(int position) {
        Toast.makeText(getContext(), "OnMenuSwitch:" + position, Toast.LENGTH_SHORT).show();

    }

    public interface OnTabPageChangeListener {
        //菜单按钮的点击事件
        void onTabPageMenuClick();
    }

    public void setOnTabPageChangeListener(OnTabPageChangeListener listener) {
        mOnTabPageChangeListener = listener;
    }
}
