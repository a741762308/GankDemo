package com.jsqix.dongqing.gank.fragment.youku;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jsqix.dongqing.gank.bean.HomeData;
import com.jsqix.dongqing.gank.fragment.BaseHomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqing on 2017/1/16.
 */

public class YoukuFragment extends BaseHomeActivity {

    @Override
    protected HomeData setUpViewPage() {
        List<String> titles = new ArrayList<>();
        titles.add("电视剧");
        titles.add("电影");
        titles.add("综艺");
        titles.add("动漫");
        titles.add("纪录片");
//        titles.add("音乐");
        titles.add("教育");
        titles.add("体育");
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            Fragment fragment = new YoukuProgramFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", titles.get(i));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return new HomeData(titles, fragments);
    }
}
