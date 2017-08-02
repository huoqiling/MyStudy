package com.example.zyfx_.mystudy.view.activity;

import android.os.Bundle;
import android.os.Handler;

import com.example.zyfx_.mystudy.R;
import com.gyf.barlibrary.ImmersionBar;
import com.jaeger.library.StatusBarUtil;
import com.sunfusheng.glideimageview.GlideImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author zhangxin
 * @date 2017/6/21 10:57
 * @description 闪屏
 **/
public class SplashActivity extends BaseActivity {


    @BindView(R.id.glideImage)
    GlideImageView glideImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start(StudyActivity.class);
            }
        },2000);
    }
}
