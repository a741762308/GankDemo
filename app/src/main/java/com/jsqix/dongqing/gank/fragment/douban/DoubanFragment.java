package com.jsqix.dongqing.gank.fragment.douban;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.adapter.TabFragmentAdapter;
import com.jsqix.dongqing.gank.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqing on 2017/1/12.
 */

public class DoubanFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {
        initViewPage();
    }
    private void initViewPage() {
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager = (ViewPager) view.findViewById(R.id.view_page);
        List<String> titles = new ArrayList<>();
        titles.add("正在热映");
        titles.add("即将上线");
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            Fragment fragment=new DoubanFilmFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", titles.get(i));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        TabFragmentAdapter adapter = new TabFragmentAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
