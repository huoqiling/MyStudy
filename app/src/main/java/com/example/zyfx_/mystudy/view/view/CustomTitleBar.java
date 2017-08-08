package com.example.zyfx_.mystudy.view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zyfx_.mystudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author zhangxin
 * @date 2017/6/30 10:58
 * @description 自定义标题栏
 **/
public class CustomTitleBar extends BaseFrameLayout {

    @BindView(R.id.btnLeftImg)
    public ImageView btnLeftImg;

    @BindView(R.id.btnLeftText)
    public TextView btnLeftText;

    @BindView(R.id.tvTitle)
    public CustomRadioButton tvTitle;

    @BindView(R.id.btnRightImg)
    public ImageView btnRightImg;

    @BindView(R.id.btnRightText)
    public TextView btnRightText;


    private String leftButtonText = "";
    private int leftButtonTextColor = Color.parseColor("#ffffff");
    private String titleText = "";
    private String rightButtonText = "";
    private int rightButtonTextColor = Color.parseColor("#ffffff");
    private boolean showLeftImage;
    private boolean showRightImage;
    private int rightImageId = 0;
    private int leftImageId = 0;

    private OnCustomTitleBarListener titleBarListener;

    public void setOnCustomTitleBarListener(OnCustomTitleBarListener titleBarListener) {
        this.titleBarListener = titleBarListener;
    }

    public CustomTitleBar(Context context) {
        this(context, null);
    }

    public CustomTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.view_title_bar, this);
        ButterKnife.bind(view);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        leftButtonText = a.getString(R.styleable.CustomTitleBar_leftButtonText);
        leftButtonTextColor = a.getColor(R.styleable.CustomTitleBar_leftButtonTextColor, leftButtonTextColor);
        titleText = a.getString(R.styleable.CustomTitleBar_titleText);
        rightButtonText = a.getString(R.styleable.CustomTitleBar_rightButtonText);
        rightButtonTextColor = a.getColor(R.styleable.CustomTitleBar_rightButtonTextColor, rightButtonTextColor);
        showLeftImage = a.getBoolean(R.styleable.CustomTitleBar_showLeftImage, true);
        showRightImage = a.getBoolean(R.styleable.CustomTitleBar_showRightImages, false);
        rightImageId = a.getResourceId(R.styleable.CustomTitleBar_rightImageSrc, 0);
        leftImageId = a.getResourceId(R.styleable.CustomTitleBar_leftImageSrc, 0);
        a.recycle();
        initView();
    }

    private void initView() {
        setTitleText(titleText);
        setRightBtnText(rightButtonText);
        if (!showLeftImage) {
            invisibleView(btnLeftImg);
        }
        if (leftImageId > 0) {
            setLeftImageResource(leftImageId);
        }

        if (!showRightImage) {
            invisibleView(btnRightImg);
        }
        if (rightImageId > 0) {
            setRightImageResource(rightImageId);
        }

    }

    public void setLeftBtnText(@StringRes int leftTextResId) {
        setLeftBtnText(getStringResources(leftTextResId));
    }

    public void setLeftBtnText(@NonNull String leftText) {
        try {
            if (!TextUtils.isEmpty(leftText)) {
                btnLeftText.setText(leftText);
                btnLeftText.setTextColor(leftButtonTextColor);
                showView(btnLeftText);
                goneView(btnLeftImg);
            } else {
                goneView(btnLeftText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitleText(@NonNull String title) {
        try {
            setTextViewData(tvTitle, title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置标题
     *
     * @param titleResId
     */
    public void setTitleText(@StringRes int titleResId) {
        setTitleText(getStringResources(titleResId));
    }

    public void setRightBtnText(@StringRes int rightBtnTextResId) {
        setRightBtnText(getStringResources(rightBtnTextResId));
    }

    public void setRightBtnText(@NonNull String text) {
        try {
            if (!TextUtils.isEmpty(text)) {
                btnRightText.setText(text);
                btnRightText.setTextColor(rightButtonTextColor);
                showView(btnRightText);
                goneView(btnRightImg);
            } else {
                goneView(btnRightText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置左边图片
     *
     * @param drawableId
     */
    public void setLeftImageResource(@DrawableRes int drawableId) {
        try {
            btnLeftImg.setImageResource(drawableId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置右边图片
     *
     * @param drawableId
     */
    public void setRightImageResource(@DrawableRes int drawableId) {
        try {
            btnRightImg.setImageResource(drawableId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 显示返回按钮
     */
    public void showLeftImage() {
        showView(btnLeftImg);
    }

    /**
     * 隐藏返回按钮
     */
    public void hideLeftImage() {
        showView(btnLeftImg);
    }

    /**
     * 显示左边文字按钮
     */
    public void showLeftText() {
        showView(btnLeftText);
    }

    /**
     * 隐藏左边文字按钮
     */
    public void hideLeftText() {
        goneView(btnLeftText);
    }


    /**
     * 隐藏右边图片
     */
    public void hideRightImage() {
        goneView(btnRightImg);
    }


    /**
     * 显示右边图片
     */
    public void showRightImage() {
        showView(btnRightImg);
    }


    /**
     * 显示右边按钮
     */
    public void showRightText() {
        showView(btnRightText);
    }


    /**
     * 隐藏右边按钮
     */
    public void hideRightText() {
        goneView(btnRightText);
    }


    @OnClick({R.id.btnLeftText, R.id.btnLeftImg, R.id.btnRightImg, R.id.btnRightText, R.id.tvTitle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLeftText:
                if (null != titleBarListener) {
                    titleBarListener.onLeftTextClick();
                }
                break;
            case R.id.btnLeftImg:
                if (null != titleBarListener) {
                    titleBarListener.onLeftImageClick();
                }
                break;
            case R.id.btnRightImg:
                if (null != titleBarListener) {
                    titleBarListener.onRightImageClick();
                }
                break;
            case R.id.btnRightText:
                if (null != titleBarListener) {
                    titleBarListener.onRightTextClick();
                }
                break;
            case R.id.tvTitle:
                if (null != titleBarListener) {
                    titleBarListener.onTitleClick();
                }
                break;
        }
    }


    public interface OnCustomTitleBarListener {

        void onLeftImageClick();

        void onLeftTextClick();

        void onRightTextClick();

        void onRightImageClick();

        void onTitleClick();
    }
}
