package com.jsqix.dongqing.gank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.adapter.TabFragmentAdapter;
import com.jsqix.dongqing.gank.bean.HomeData;

/**
 * Created by dongqing on 2017/1/16.
 */

public abstract class BaseHomeActivity extends BaseFragment {
    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {

        initViewPage();
    }

    protected abstract HomeData setUpViewPage();

    private void initViewPage() {
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.view_page);
        HomeData homeData = setUpViewPage();
        TabFragmentAdapter adapter = new TabFragmentAdapter(getChildFragmentManager(), homeData.getFragments(), homeData.getTitles());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
