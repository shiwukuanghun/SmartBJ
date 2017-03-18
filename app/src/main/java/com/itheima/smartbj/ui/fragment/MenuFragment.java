package com.itheima.smartbj.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.smartbj.R;

import butterknife.BindView;

/**
 * Created by HuangBin on 2017/3/16 23:58.
 */

public class MenuFragment extends BaseFragment {
    @BindView(R.id.lv_menu)
    ListView mLvMenu;

    private String[] mMenuTtitles = {"新闻", "专题", "主题", "互动"};
    private int mLastPosition;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void init() {
        super.init();
        mLvMenu.setAdapter(mBaseAdapter);
        mLvMenu.setOnItemClickListener(mOnItemClickListener);
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            boolean isSwitch = (mLastPosition != position);

            //通知外界发生菜单选项点击
            if(mOnMenuChangeListener != null) {
                mOnMenuChangeListener.onMenuItemSwitch(position, isSwitch);
            }
            //如果点击的孩子就是上次的孩子则不处理颜色的变化
            if(isSwitch == false) {
                return;
            }
            view.setEnabled(true);
//            View clickChild = parent.getChildAt(position);
//            clickChild.setEnabled(true);
            View preChild = parent.getChildAt(mLastPosition);
            //将上一个设置为不可用
            preChild.setEnabled(false);
            //将上一位置的position赋值给mLastPosition
            mLastPosition = position;
        }
    };

    private BaseAdapter mBaseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mMenuTtitles.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = View.inflate(getContext(), R.layout.view_menu_item, null);
            }
            ((TextView)convertView).setText(mMenuTtitles[position]);
            if(position == 0) {
                convertView.setEnabled(true);
            } else {
                convertView.setEnabled(false);
            }
            return convertView;
        }
    };

    public interface OnMenuChangeListener {
        /**
         *
         * @param isSwitch 通知外界是否发生了切换
         */
        void onMenuItemSwitch(int position, boolean isSwitch);
    }

    private OnMenuChangeListener mOnMenuChangeListener;

    public void setOnMenuChangeListener(OnMenuChangeListener listener) {
        mOnMenuChangeListener = listener;
    }
}
