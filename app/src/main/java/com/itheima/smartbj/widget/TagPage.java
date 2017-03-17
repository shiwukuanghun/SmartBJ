package com.itheima.smartbj.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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

    public TagPage(Context context) {
        this(context, null);
    }

    public TagPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        ButterKnife.bind(this, this);
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_tab_page, this);
    }

    @OnClick(R.id.menu)
    public void onClick() {
    }

    public void hideMenu() {
        mMenu.setVisibility(View.GONE);
    }
}
