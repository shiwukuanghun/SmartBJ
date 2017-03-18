package com.itheima.smartbj.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HuangBin on 2017/3/18 12:10.
 */

public class NewsCenterTabPage extends TagPage {
    public NewsCenterTabPage(Context context) {
        super(context);
    }

    public NewsCenterTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void OnMenuSwitch(int position) {
        super.OnMenuSwitch(position);
        //TextView textView =  new TextView(getContext());
        View view = null;
        switch (position) {
            case 0:
                //textView.setText("新闻");
                NewsPage newsPage = new NewsPage(getContext());
                view = newsPage;
                break;
            case 1:
                TextView zhuanti = new TextView(getContext());
                zhuanti.setText("专题");
                view = zhuanti;
                break;
            case 2:
                TextView zhuti = new TextView(getContext());
                zhuti.setText("主题");
                view = zhuti;
                break;
            case 3:
                TextView hudong = new TextView(getContext());
                hudong.setText("互动");
                view = hudong;
                break;
        }
        mTabPageContentFrame.removeAllViews();
        //mTabPageContentFrame.addView(textView);
        mTabPageContentFrame.addView(view);
    }
}
