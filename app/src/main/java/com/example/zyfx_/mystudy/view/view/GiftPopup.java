package com.example.zyfx_.mystudy.view.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.zyfx_.mystudy.R;
import com.example.zyfx_.mystudy.utils.DensityUtil;
import com.example.zyfx_.mystudy.view.adapter.GiftAdapter;
import com.zyyoona7.lib.BaseCustomPopup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyyoona7 on 2017/8/7.
 */

public class GiftPopup extends BaseCustomPopup {

    private RecyclerView mRecyclerView;

    public GiftPopup(Context context) {
        super(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_gift);
        setHeight(DensityUtil.dip2px(300));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusAndOutsideEnable(true);
    }

    @Override
    protected void initViews(View view) {
        mRecyclerView = getView(R.id.rv_gift);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false));
        List<String> list = createList();
        GiftAdapter adapter = new GiftAdapter();
        adapter.setNewData(list);
        mRecyclerView.setAdapter(adapter);
    }

    private List<String> createList() {
        List<String> list = new ArrayList<>(1);
        for (int i = 0; i < 15; i++) {
            list.add("");
        }
        return list;
    }
}
