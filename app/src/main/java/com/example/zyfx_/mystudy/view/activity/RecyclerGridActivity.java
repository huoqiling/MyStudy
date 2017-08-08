package com.example.zyfx_.mystudy.view.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zyfx_.mystudy.R;
import com.example.zyfx_.mystudy.view.entitys.GridItem;
import com.example.zyfx_.mystudy.view.view.CustomTitleBar;
import com.example.zyfx_.mystudy.view.view.RecyclerViewItemDecoration;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zyfx_ on 2017/8/8.
 */
public class RecyclerGridActivity extends BaseActivity {

    @BindView(R.id.titleBar)
    CustomTitleBar titleBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<GridItem> gridItemList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_grid);
        ButterKnife.bind(this);
        init();
        initData();
        initRecyclerView();
    }

    private void init() {
        ImmersionBar.with(this).statusBarColor(R.color.colorAccent).fitsSystemWindows(true).init();
        titleBar.setOnCustomTitleBarListener(new CustomTitleBar.OnCustomTitleBarListener() {
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
        });
    }

    private void initData() {
        gridItemList.add(new GridItem(R.mipmap.iv_home_assets, "资产"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_declaration, "报单"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_advertising_coupon, "广告"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_team_performance, "团队业绩"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_profit, "收益"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_singlerow, "激活"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_deposit_withdrawal, "充值提现"));
        gridItemList.add(new GridItem(R.mipmap.iv_home_transfer_accounts, "转账"));
        gridItemList.add(new GridItem(0, ""));

    }

    private void initRecyclerView() {
        MyGridAdapter adapter = new MyGridAdapter(gridItemList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        RecyclerViewItemDecoration itemDecoration = new RecyclerViewItemDecoration();
        recyclerView.addItemDecoration(itemDecoration.new Builder(this)
                .mode(RecyclerViewItemDecoration.MODE_GRID)
                .color(Color.RED)
                .thickness(3)
                .gridBottomVisible(true)
                .gridTopVisible(false)
                .gridLeftVisible(false)
                .gridRightVisible(false)
                .create());
        recyclerView.setAdapter(adapter);
    }


    private class MyGridAdapter extends BaseQuickAdapter<GridItem, BaseViewHolder> {

        public MyGridAdapter(List<GridItem> gridItemList) {
            super(R.layout.item_autoflowloyout_grid, gridItemList);
        }

        @Override
        protected void convert(BaseViewHolder helper, GridItem item) {
            if (item.getIconRes() > 0 && !TextUtils.isEmpty(item.getIconName())) {
                helper.setImageResource(R.id.ivPicture, item.getIconRes());
                helper.setText(R.id.tvName, item.getIconName());
            }
        }
    }
}
