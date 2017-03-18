package com.itheima.smartbj.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.itheima.smartbj.R;
import com.itheima.smartbj.ui.fragment.HomeFragment;
import com.itheima.smartbj.ui.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    private HomeFragment mHomeFragment;
    private MenuFragment mMenuFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {  //注意：要用只有一个参数的
        super.onCreate(savedInstanceState);
        initLeftMenu();
        initSlidingMenu();
        initContent();
        //getSlidingMenu().toggle(); 打开或者关闭侧滑菜单
        initListener();
    }

    private void initListener() {
        mHomeFragment.setOnHomeChangeListener(new HomeFragment.OnHomeChangeListener() {
            @Override
            public void onTabSwitch(int checkedId) {
                if(R.id.tab_home == checkedId || R.id.tab_setting_center == checkedId){
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE); //不能拉出
                } else {
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//从边缘拉出
                }

            }

            @Override
            public void onTabPageMenuClick() {
                //Toast.makeText(MainActivity.this, "TabPage菜单按钮事件从HomeFragment传递到了MainActivity", Toast.LENGTH_SHORT).show();
                //打开或者关闭侧滑菜单
                getSlidingMenu().toggle();
            }
        });

        //点击菜单选项关闭菜单栏的监听事件
        mMenuFragment.setOnMenuChangeListener(new MenuFragment.OnMenuChangeListener() {
            @Override
            public void onMenuItemSwitch(int position, boolean isSwitch) {
                //打开或关闭侧滑菜单
                getSlidingMenu().toggle();
                //通知HomeFagment切换的是哪一个菜单
                if(isSwitch) {
                    mHomeFragment.onMenuSwitch(position);
                }
            }
        });
    }

    /**
     * 初始化内容的布局
     */
    private void initContent() {
        setContentView(R.layout.content_frame);
        mHomeFragment =  new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame,mHomeFragment)
                .commit();
    }

    /**
     * 初始化左边菜单布局
     */
    private void initLeftMenu() {
        setBehindContentView(R.layout.menu_frame); //设置侧滑菜单的布局
        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        mMenuFragment = new MenuFragment();
        t.replace(R.id.menu_frame, mMenuFragment);
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
