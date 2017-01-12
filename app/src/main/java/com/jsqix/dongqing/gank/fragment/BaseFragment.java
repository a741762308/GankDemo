package com.jsqix.dongqing.gank.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dongqing on 2016/12/8.
 * A simple {@link Fragment} subclass.
 */

public abstract class BaseFragment extends Fragment {
    protected View view;

    protected Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    protected abstract void initView();
}
