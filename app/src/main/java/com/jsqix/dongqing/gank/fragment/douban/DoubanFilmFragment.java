package com.jsqix.dongqing.gank.fragment.douban;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.google.gson.Gson;
import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.adapter.recycler.DoubanFilmAdapter;
import com.jsqix.dongqing.gank.api.Api;
import com.jsqix.dongqing.gank.bean.douban.DoubanFilm;
import com.jsqix.dongqing.gank.fragment.RefreshFragment;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dongqing on 2017/1/12.
 */

public class DoubanFilmFragment extends RefreshFragment {
    private List<DoubanFilm.SubjectsBean> data = new ArrayList<>();
    private DoubanFilmAdapter adapter;

    @Override
    protected void initView() {
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOverScrollBottomShow(false);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        adapter = new DoubanFilmAdapter(mContext, R.layout.item_recycler_douban_film, data);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                getData();
            }
        });
    }

    private void getData() {
        if ("正在热映".equals(categoryType)) {
            getWell();
        } else if ("即将上线".equals(categoryType)) {
            getCome();
        }
    }

    private void getWell() {
        Api.getInstance().DOUBAN.service.getFilmWell()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanFilm>() {
                    @Override
                    public void onCompleted() {
                        Log.v("success:", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("failure:", "onError");
                        refreshLayout.finishRefreshing();
                    }

                    @Override
                    public void onNext(DoubanFilm doubanFilm) {
                        Log.v("next:", new Gson().toJson(doubanFilm));
                        refreshLayout.finishRefreshing();
                        data.clear();
                        data.addAll(doubanFilm.getSubjects());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void getCome() {
        Api.getInstance().DOUBAN.service.getFilmCome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanFilm>() {
                    @Override
                    public void onCompleted() {
                        Log.v("success:", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("failure:", "onError");
                        refreshLayout.finishRefreshing();
                    }

                    @Override
                    public void onNext(DoubanFilm doubanFilm) {
                        Log.v("next:", new Gson().toJson(doubanFilm));
                        refreshLayout.finishRefreshing();
                        data.clear();
                        data.addAll(doubanFilm.getSubjects());
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
