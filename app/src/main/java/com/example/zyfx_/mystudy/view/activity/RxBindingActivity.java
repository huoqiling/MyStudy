package com.example.zyfx_.mystudy.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.zyfx_.mystudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zyfx_ on 2017/8/9.
 */
public class RxBindingActivity extends BaseActivity {

    @BindView(R.id.btnAntiShake)
    Button btnAntiShake;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_binding);
        ButterKnife.bind(this);

    }
}
