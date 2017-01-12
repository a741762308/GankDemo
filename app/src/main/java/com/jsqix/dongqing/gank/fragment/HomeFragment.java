package com.jsqix.dongqing.gank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.adapter.TabFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqing on 2017/1/10.
 */

public class HomeFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

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
    private void initViewPage() {
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.view_page);
        List<String> titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("Android");
        titles.add("iOS");
        titles.add("福利");
        titles.add("前端");
        titles.add("休息视频");
        titles.add("拓展资源");
        titles.add("App");
        titles.add("瞎推荐");
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            Fragment fragment;
            if (titles.get(i).equals("推荐")) {
                fragment = new RecommendFragment();
            } else if (titles.get(i).equals("福利")) {
                fragment = new FuliFragment();
            } else {
                fragment = new CommonFragment();
            }
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
