package com.itheima.smartbj.ui;

import android.os.SystemClock;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.itheima.smartbj.BaseActivity;
import com.itheima.smartbj.MainActivity;
import com.itheima.smartbj.R;
import com.itheima.smartbj.utils.SPUtils;

import butterknife.BindView;


/**
 * Created by HuangBin on 2017/3/16 19:00.
 */

public class SplashActivity extends BaseActivity {

    private static final int ANIMATION_DURATION = 2000;

    @BindView(R.id.splash_image)
    ImageView mSplashImage;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    protected  void init() {
        startAnimation();
    }

    /**
     * 旋转动画
     */
    private void startAnimation() {
        //创建动画集合
        AnimationSet animationSet = new AnimationSet(false);
        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1,
                0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        //透明度渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(ANIMATION_DURATION);
        mSplashImage.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SystemClock.sleep(500);
                //动画播放完毕后消失
               /* Intent intent = new Intent(SplashActivity.this, TutorialActivity.class);
                startActivity(intent);
                finish();*/
                //如果第一次进来就进入向导页，否则直接进入主页，获取状态是否保存进入过
                boolean started = SPUtils.getBoolean(SplashActivity.this, "started", false);
                if(started == true) { //已经进入过
                    navigateTo(MainActivity.class);
                } else {
                    navigateTo(TutorialActivity.class);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
