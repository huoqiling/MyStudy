package com.example.zyfx_.mystudy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.zyfx_.mystudy.R;
import com.gyf.barlibrary.ImmersionBar;
import com.jaeger.library.StatusBarUtil;

/**
 * Created by zyfx_ on 2017/6/21.
 */
public class BaseActivity extends AppCompatActivity {

    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        immersionBar = ImmersionBar.with(this);
        immersionBar.init();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    protected void start(Class<? extends Activity> activityClass) {
        Intent intent = new Intent();
        intent.setClass(this, activityClass);
        startActivity(intent);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        immersionBar.destroy();
    }
}
