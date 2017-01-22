package com.jsqix.dongqing.gank.fragment.douban;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.jsqix.dongqing.gank.bean.HomeData;
import com.jsqix.dongqing.gank.fragment.BaseHomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqing on 2017/1/12.
 */

public class DoubanFragment extends BaseHomeActivity {

    @Override
    protected HomeData setUpViewPage() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        List<String> titles = new ArrayList<>();
        titles.add("正在热映");
        titles.add("即将上线");
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            Fragment fragment = new DoubanFilmFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", titles.get(i));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return new HomeData(titles, fragments);
    }
}
