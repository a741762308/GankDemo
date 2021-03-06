package com.jsqix.dongqing.gank.fragment;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.google.gson.Gson;
import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.adapter.recycler.RecyclerFuliAdapter;
import com.jsqix.dongqing.gank.api.Api;
import com.jsqix.dongqing.gank.bean.gank.CategorylData;
import com.jsqix.dongqing.gank.bean.gank.DataBean;
import com.jsqix.dongqing.gank.bean.gank.ImgDataBean;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dongqing on 2016/12/8.
 * 福利妹纸
 */

public class FuliFragment extends RefreshFragment {
    private RecyclerFuliAdapter adapter;
    private List<DataBean> data = new ArrayList<>();

    public FuliFragment() {
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerFuliAdapter(mContext, R.layout.item_recycler_fuli, data);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                page=1;
                getData();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                page++;
                getData();
            }
        });
    }

    private void getData() {
        Api.getInstance().GANK.service.getCategoryData(categoryType, page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<CategorylData, List<ImgDataBean>>() {
                    @Override
                    public List<ImgDataBean> call(CategorylData categorylData) {
                        return categorylData.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ImgDataBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.v("success:", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("failure:", e.toString());
                        refreshLayout.finishRefreshing();
                        refreshLayout.finishLoadmore();
                    }

                    @Override
                    public void onNext(List<ImgDataBean> imgDataBeen) {
                        Log.v("result:", new Gson().toJson(imgDataBeen));
                        if (page == 1) {
                            data.clear();
                            refreshLayout.finishRefreshing();
                        }else {
                            refreshLayout.finishLoadmore();
                        }
                        data.addAll(imgDataBeen);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
