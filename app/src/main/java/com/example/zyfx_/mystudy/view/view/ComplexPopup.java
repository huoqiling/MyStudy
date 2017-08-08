package com.example.zyfx_.mystudy.view.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zyfx_.mystudy.R;
import com.example.zyfx_.mystudy.utils.DensityUtil;
import com.zyyoona7.lib.BaseCustomPopup;

/**
 * Created by zyyoona7 on 2017/8/4.
 */

public class ComplexPopup extends BaseCustomPopup {
    private static final String TAG = "ComplexPopup";

    private Button mOkBtn;
    private Button mCancelBtn;

    public ComplexPopup(Context context) {
        super(context);
    }


    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_complex, ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(300));
        setFocusAndOutsideEnable(false)
                .setBackgroundDimEnable(true)
                .setDimValue(0.5f);

    }

    @Override
    protected void initViews(View view) {
        mOkBtn = getView(R.id.btn_ok);
        mCancelBtn = getView(R.id.btn_cancel);

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setAbc(){

    }

}
