package com.example.zyfx_.mystudy.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zyfx_.mystudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zyfx_ on 2017/7/31.
 */
public class StudyActivity extends BaseActivity {

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @BindView(R.id.btnAirShip)
    Button btnAirShip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btnLogin, R.id.btnAirShip,R.id.btnAutoFlowLayout,R.id.btnEasyPopup,R.id.btnRecyclerGird})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                start(LoginActivity.class);
                break;
            case R.id.btnAirShip:
                start(AirShipActivity.class);
                break;
            case R.id.btnAutoFlowLayout:
                start(AutoFlowLayoutActivity.class);
                break;
            case R.id.btnEasyPopup:
                start(EasyPopupActivity.class);
                break;
            case R.id.btnRecyclerGird:
                start(RecyclerGridActivity.class);
                break;
        }


    }
}
