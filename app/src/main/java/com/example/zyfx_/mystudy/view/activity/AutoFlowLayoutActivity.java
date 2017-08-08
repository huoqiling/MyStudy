package com.example.zyfx_.mystudy.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;
import com.example.zyfx_.mystudy.R;
import com.example.zyfx_.mystudy.view.entitys.GridItem;
import com.example.zyfx_.mystudy.view.view.CustomTitleBar;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author zhangxin
 * @date 2017/8/7 11:24
 * @description 自定义ViewGroup, 实现多功能流式布局与网格布局
 **/
public class AutoFlowLayoutActivity extends BaseActivity implements CustomTitleBar.OnCustomTitleBarListener {

    @BindView(R.id.afLayout)
    AutoFlowLayout afLayout;

    @BindView(R.id.titleBar)
    CustomTitleBar titleBar;

    private List<GridItem> gridItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_flow_layout);
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarColor(R.color.colorAccent).fitsSystemWindows(true).init();
        titleBar.setOnCustomTitleBarListener(this);
        initLayout();
    }


    private void initLayout() {
        gridItemList.add(new GridItem(R.mipmap.iv_home_assets, "资产"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_declaration, "报单"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_advertising_coupon, "广告"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_team_performance, "团队业绩"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_profit, "收益"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_singlerow, "激活"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_deposit_withdrawal, "充值提现"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_transfer_accounts, "转账"));
        gridItemList.add(new GridItem(0, ""));

        afLayout.setAdapter(new GridAdapter(gridItemList));
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

    }

    @Override
    public void onTitleClick() {

    }

    private class GridAdapter extends FlowAdapter<GridItem> {

        private View itemView = null;

        public GridAdapter(List<GridItem> dataList) {
            super(dataList);
        }

        @Override
        public View getView(int position) {
            itemView = getLayoutInflater().inflate(R.layout.item_autoflowloyout_grid, null);
            TextView tvName = (TextView) itemView.findViewById(R.id.tvName);
            ImageView ivPicture = (ImageView) itemView.findViewById(R.id.ivPicture);
            GridItem gridItem = gridItemList.get(position);
            if (gridItem.getIconRes() > 0 && !TextUtils.isEmpty(gridItem.getIconName())) {
                ivPicture.setImageResource(gridItem.getIconRes());
                tvName.setText(gridItem.getIconName());
            }
            return itemView;
        }


    }
}
