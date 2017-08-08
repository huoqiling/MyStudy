package com.example.zyfx_.mystudy.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.zyfx_.mystudy.R;
import com.example.zyfx_.mystudy.utils.DensityUtil;
import com.example.zyfx_.mystudy.utils.ToastUtil;
import com.example.zyfx_.mystudy.view.view.ComplexPopup;
import com.example.zyfx_.mystudy.view.view.CustomTitleBar;
import com.example.zyfx_.mystudy.view.view.GiftPopup;
import com.gyf.barlibrary.ImmersionBar;
import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zyfx_ on 2017/8/8.
 */
public class EasyPopupActivity extends BaseActivity implements CustomTitleBar.OnCustomTitleBarListener {

    @BindView(R.id.btn_circle_comment)
    Button btnCircleComment;

    @BindView(R.id.btn_above)
    Button btnAbove;

    @BindView(R.id.btn_right)
    Button btnRight;

    @BindView(R.id.btn_bg_dim)
    Button btnBgDim;

    @BindView(R.id.btn_bg_dim_any)
    Button btnBgDimAny;

    @BindView(R.id.btn_gift)
    Button btnGift;

    @BindView(R.id.ll_complex_bg_dim)
    LinearLayout llComplexBgDim;

    @BindView(R.id.btn_complex)
    Button btnComplex;

    @BindView(R.id.titleBar)
    CustomTitleBar titleBar;


    private EasyPopup mWeiboPop;
    private EasyPopup mQQPop;
    private EasyPopup mCirclePop;
    private EasyPopup mAbovePop;
    private EasyPopup mBgDimPop;
    private EasyPopup mAnyBgDimPop;
    private GiftPopup mGiftPopup;
    private ComplexPopup mComplexPopup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_popup);
        ButterKnife.bind(this);
        titleBar.setOnCustomTitleBarListener(this);
        ImmersionBar.with(this).statusBarColor(R.color.colorAccent).fitsSystemWindows(true).init();
        initCirclePop();
        initAbovePop();
        initBgDimPop();
        initAnyBgDimPop();
        initQQPop();
        initWeiBoPop();
        initGiftPop();
        initComplexPop();
    }

    private void initCirclePop() {
        mCirclePop = new EasyPopup(this)
                .setContentView(R.layout.layout_circle_comment)
                .setAnimationStyle(R.style.CirclePopAnim)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                .createPopup();
        CommentViewHolder viewHolder = new CommentViewHolder(mCirclePop.getContentView());
        viewHolder.tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showTextToast("评论");
                mCirclePop.dismiss();
            }
        });
        viewHolder.tvPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showTextToast("点赞");
                mCirclePop.dismiss();
            }
        });
    }

    private void initAbovePop() {
        mAbovePop = new EasyPopup(this)
                .setContentView(R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.e("zhangx", "onDismiss: mAbovePop");
                    }
                }).createPopup();
        AnyViewHolder viewHolder = new AnyViewHolder(mAbovePop.getContentView());
        viewHolder.tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showTextToast("点击了第一个");
                mAbovePop.dismiss();
            }
        });
        viewHolder.tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showTextToast("点击了第二个");
                mAbovePop.dismiss();
            }
        });
    }


    private void initBgDimPop() {
        mBgDimPop = new EasyPopup(this)
                .setContentView(R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.4f)
                .createPopup();
    }

    private void initAnyBgDimPop() {
        mAnyBgDimPop = new EasyPopup(this)
                .setContentView(R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.4f)
                .setDimView(titleBar)
                .setDimColor(Color.YELLOW)
                .createPopup();
    }

    private void initQQPop() {
        mQQPop = new EasyPopup(this)
                .setContentView(R.layout.layout_right_pop)
                .setAnimationStyle(R.style.QQPopAnim)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.5f)
                .setDimColor(Color.RED)
                .setDimView(titleBar)
                .createPopup();

    }

    private void initWeiBoPop() {
        mWeiboPop = new EasyPopup(this)
                .setContentView(R.layout.layout_center_pop)
                .setAnimationStyle(R.style.WeiboPopAnim)
                .setFocusAndOutsideEnable(true)
                .createPopup();
    }

    private void initGiftPop() {
        mGiftPopup = new GiftPopup(this).createPopup();
    }

    private void initComplexPop() {
        mComplexPopup = new ComplexPopup(this).setDimView(llComplexBgDim).createPopup();
    }

    private void showCirclePop(View view) {
        mCirclePop.showAtAnchorView(view, VerticalGravity.CENTER, HorizontalGravity.CENTER, 0, 0);
    }

    private void showAbovePop(View view) {
        mAbovePop.showAtAnchorView(view, VerticalGravity.ABOVE, HorizontalGravity.CENTER);
    }

    private void showRightPop(View view) {
        mAbovePop.showAtAnchorView(view, VerticalGravity.CENTER, HorizontalGravity.RIGHT);
    }

    private void showBgDimPop(View view) {
        mBgDimPop.showAtAnchorView(view, VerticalGravity.ALIGN_TOP, HorizontalGravity.ALIGN_LEFT);
    }

    private void showAnyBgDimPop(View view) {
        mAnyBgDimPop.showAtAnchorView(view, VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.ALIGN_RIGHT);
    }

    private void showQQPop(View view) {
        mQQPop.showAtAnchorView(view, VerticalGravity.BELOW, HorizontalGravity.LEFT, DensityUtil.dip2px(30), 0);
    }

    private void showWeiBoPop(View view) {
        mWeiboPop.showAtAnchorView(view, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, DensityUtil.dip2px(10));
    }

    private void showGiftPop(View view) {
        mGiftPopup.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void showComplexPop(View view) {
        mComplexPopup.showAtAnchorView(view, VerticalGravity.ABOVE, HorizontalGravity.LEFT);
    }

    @OnClick({R.id.btn_circle_comment, R.id.btn_above, R.id.btn_right, R.id.btn_bg_dim, R.id.btn_bg_dim_any, R.id.btn_gift, R.id.btn_complex})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_circle_comment:
                showCirclePop(view);
                break;
            case R.id.btn_above:
                showAbovePop(view);
                break;
            case R.id.btn_right:
                showRightPop(view);
                break;
            case R.id.btn_bg_dim:
                showBgDimPop(view);
                break;
            case R.id.btn_bg_dim_any:
                showAnyBgDimPop(view);
                break;
            case R.id.btn_gift:
                showGiftPop(view);
                break;
            case R.id.btn_complex:
                showComplexPop(view);
                break;
        }
    }

    @Override
    public void onLeftImageClick() {
        finish();
    }

    @Override
    public void onLeftTextClick() {

    }

    @Override
    public void onRightTextClick() {

    }

    @Override
    public void onRightImageClick() {
        showQQPop(titleBar.btnRightImg);
    }

    @Override
    public void onTitleClick() {
        showWeiBoPop(titleBar.tvTitle);
    }

    static class CommentViewHolder {

        @BindView(R.id.tvPraise)
        TextView tvPraise;

        @BindView(R.id.tvComment)
        TextView tvComment;

        CommentViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class AnyViewHolder {

        @BindView(R.id.tvOne)
        TextView tvOne;

        @BindView(R.id.tvTwo)
        TextView tvTwo;

        AnyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
