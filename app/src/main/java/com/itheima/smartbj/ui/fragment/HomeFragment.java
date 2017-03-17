package com.itheima.smartbj.ui.fragment;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.itheima.smartbj.R;
import com.itheima.smartbj.widget.TagPage;

import butterknife.BindView;

/**
 * Created by HuangBin on 2017/3/17 00:05.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.tab_container)
    RadioGroup mTabContainer;
    @BindView(R.id.tab_page_container)
    FrameLayout mTabPageContainer;

    private SparseArray<TagPage> mTagPageCache = new SparseArray<>();
    private static final String TAG = "HomeFragment";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        super.init();
        mTabContainer.setOnCheckedChangeListener(mOnCheckedChangeListener);
        mTabContainer.check(R.id.tab_home);  //默认check到首页，这句要写在监听事件之后
    }

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            /*//添加TagPage到FrameLayout里面
            TagPage tagPage = new TagPage(getContext());
            //先移除所有的TagPage
            mTabPageContainer.removeAllViews();*/
            TagPage tagPage = null;
            if(mTagPageCache.get(checkedId) != null) {//如果已经缓存了就直接获取
                tagPage = mTagPageCache.get(checkedId);
                Toast.makeText(getContext(), "从缓存中获取tagPage", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCheckedChanged: " + "从缓存中获取tagPage");
            } else {
                //创建tagPage对象
                tagPage = createTagPage(checkedId);
                Toast.makeText(getContext(), "创建新的tagPage存入缓存", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCheckedChanged: " + "创建新的tagPage存入缓存");
            }
            //先移除所有的TagPage
            mTabPageContainer.removeAllViews();
            mTabPageContainer.addView(tagPage);
        }
    };

    @NonNull
    private TagPage createTagPage(int checkedId) {
        //添加TabPage到FrameLayout里面
        TagPage tagPage = new TagPage(getContext());
        //首页和设置中心没有标题栏的菜单按钮
        switch (checkedId) {
            case R.id.tab_home:
                tagPage.hideMenu();
                break;
            case R.id.tab_news_center:
                break;
            case R.id.tab_smart_service:
                break;
            case R.id.tab_gov_affairs:
                break;
            case R.id.tab_setting_center:
                tagPage.hideMenu();
                break;
        }
        //将tagPage添加到缓存
        mTagPageCache.put(checkedId, tagPage);
        return tagPage;
    }


}
