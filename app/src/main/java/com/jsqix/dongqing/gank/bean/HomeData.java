package com.jsqix.dongqing.gank.bean;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by dongqing on 2017/1/16.
 */

public class HomeData {
    private List<String> titles;
    private List<Fragment> fragments;

    public HomeData(List<String> titles, List<Fragment> fragments) {
        this.titles = titles;
        this.fragments = fragments;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}
