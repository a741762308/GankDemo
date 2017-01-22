package com.jsqix.dongqing.gank.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsqix.dongqing.gank.R;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

/**
 * Created by dongqing on 2017/1/10.
 */

public abstract class RefreshFragment extends BaseFragment {
    protected RecyclerView recyclerView;
    protected TwinklingRefreshLayout refreshLayout;

    protected String categoryType;
    protected int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_base, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        refreshLayout = (TwinklingRefreshLayout) view.findViewById(R.id.refresh_layout);
        refreshLayout.setFloatRefresh(true);
        ProgressLayout headerView = new ProgressLayout(mContext);
        headerView.setColorSchemeColors(getResources().getIntArray(R.array.material_colors));
        refreshLayout.setHeaderView(headerView);
        refreshLayout.startRefresh();
        refreshLayout.setOverScrollBottomShow(false);
        if (getArguments() != null) {
            categoryType = getArguments().getString("type", "all");
        }
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
