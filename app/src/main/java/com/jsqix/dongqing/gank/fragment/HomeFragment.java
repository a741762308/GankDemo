package com.jsqix.dongqing.gank.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jsqix.dongqing.gank.bean.HomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongqing on 2017/1/10.
 */

public class HomeFragment extends BaseHomeActivity {

    @Override
    protected HomeData setUpViewPage() {
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
        return new HomeData(titles, fragments);
    }


}
