package com.itheima.smartbj.ui.fragment;

import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.itheima.smartbj.R;
import com.itheima.smartbj.widget.GovAffairsTabPage;
import com.itheima.smartbj.widget.HomeTabPage;
import com.itheima.smartbj.widget.NewsCenterTabPage;
import com.itheima.smartbj.widget.SettingCenterTabPage;
import com.itheima.smartbj.widget.SmartServiceTabPage;
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
    private OnHomeChangeListener mOnHomeChangeListener;
    private TagPage mCurrentTagPage;

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
//                Toast.makeText(getContext(), "从缓存中获取tagPage", Toast.LENGTH_SHORT).show();
            } else {
                //创建tagPage对象
                tagPage = createTagPage(checkedId);
//                Toast.makeText(getContext(), "创建新的tagPage存入缓存", Toast.LENGTH_SHORT).show();
            }

            mCurrentTagPage = tagPage;
            //先移除所有的TagPage
            mTabPageContainer.removeAllViews();
            mTabPageContainer.addView(tagPage);

            tagPage.setOnTabPageChangeListener(new TagPage.OnTabPageChangeListener() {
                @Override
                public void onTabPageMenuClick() {
                    //Toast.makeText(getContext(), "事件传递到了HomeFragment", Toast.LENGTH_SHORT).show();
                    //通知到HomeFragment外界发生菜单按钮的点击事件
                    if(mOnHomeChangeListener != null) {
                        mOnHomeChangeListener.onTabPageMenuClick();
                    }
                }
            });

            if(mOnHomeChangeListener != null) {
                mOnHomeChangeListener.onTabSwitch(checkedId);
            }
        }
    };

    @NonNull
    private TagPage createTagPage(int checkedId) {
        //添加TabPage到FrameLayout里面
        //TagPage tagPage = new TagPage(getContext());
        TagPage tagPage = null;
        //首页和设置中心没有标题栏的菜单按钮
        switch (checkedId) {
            case R.id.tab_home:
                tagPage = new HomeTabPage(getContext());
                tagPage.setTitle("首页");
                tagPage.hideMenu();
                break;
            case R.id.tab_news_center:
                tagPage = new NewsCenterTabPage(getContext());
                tagPage.setTitle("新闻中心");
                break;
            case R.id.tab_smart_service:
                tagPage = new SmartServiceTabPage(getContext());
                tagPage.setTitle("智慧服务");
                break;
            case R.id.tab_gov_affairs:
                tagPage = new GovAffairsTabPage(getContext());
                tagPage.setTitle("政务");
                break;
            case R.id.tab_setting_center:
                tagPage = new SettingCenterTabPage(getContext());
                tagPage.hideMenu();
                tagPage.setTitle("设置中心");
                break;
        }
        //将tagPage添加到缓存
        mTagPageCache.put(checkedId, tagPage);
        return tagPage;
    }

    public void onMenuSwitch(int position) {
        Toast.makeText(getContext(), "切换到了菜单" + position, Toast.LENGTH_SHORT).show();
        mCurrentTagPage.OnMenuSwitch(position);
    }


    public interface OnHomeChangeListener {
        /**
         * tab按钮切换的回调
         * @param checkId 切换到radio button的id
         */
        void onTabSwitch(int checkedId);
        /**
         * TabPage菜单按钮的点击事件
         */
        void  onTabPageMenuClick();
    }

    public void setOnHomeChangeListener(OnHomeChangeListener listener) {
        mOnHomeChangeListener = listener;
    }

}
