package com.itheima.smartbj;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.itheima.smartbj.ui.fragment.HomeFragment;
import com.itheima.smartbj.ui.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {  //注意：要用只有一个参数的
        super.onCreate(savedInstanceState);
        initLeftMenu();
        initSlidingMenu();
        initContent();
    }

    /**
     * 初始化内容的布局
     */
    private void initContent() {
        setContentView(R.layout.content_frame);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame,homeFragment)
                .commit();
    }

    /**
     * 初始化左边菜单布局
     */
    private void initLeftMenu() {
        setBehindContentView(R.layout.menu_frame); //设置侧滑菜单的布局
        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        MenuFragment menuFragment = new MenuFragment();
        t.replace(R.id.menu_frame,menuFragment);
        t.commit();
    }

    /**
     * 配置侧滑菜单
     */
    private void initSlidingMenu() {
        SlidingMenu sm = getSlidingMenu();
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);  //侧滑菜单的偏移量
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        getSlidingMenu().setMode(SlidingMenu.LEFT);
    }
}
