package com.jsqix.dongqing.gank.fragment.youku;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.gson.Gson;
import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.adapter.recycler.YoukuVideoAdapter;
import com.jsqix.dongqing.gank.api.Api;
import com.jsqix.dongqing.gank.bean.youku.YKVideo;
import com.jsqix.dongqing.gank.fragment.RefreshFragment;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dongqing on 2017/1/16.
 */

public class YoukuVedioFragment extends RefreshFragment {
    private List<YKVideo.VideosBean> data = new ArrayList<>();
    private YoukuVideoAdapter adapter;

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new YoukuVideoAdapter(mContext, R.layout.item_recycler_normal, data);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                page = 1;
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
        Api.getInstance().YOUKU.service.getYKViedoByCategory("655690f8143987c6", categoryType, page, Api.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .map(new Func1<YKVideo, List<YKVideo.VideosBean>>() {

                    @Override
                    public List<YKVideo.VideosBean> call(YKVideo ykVideo) {
                        return ykVideo.getVideos();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<YKVideo.VideosBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.v("success:", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("failure:", "onError");
                        refreshLayout.finishRefreshing();
                        refreshLayout.finishLoadmore();
                    }

                    @Override
                    public void onNext(List<YKVideo.VideosBean> videosBean) {
                        Log.v("result:", new Gson().toJson(videosBean));
                        if (page == 1) {
                            data.clear();
                            refreshLayout.finishRefreshing();
                        } else {
                            refreshLayout.finishLoadmore();
                        }
                        data.addAll(videosBean);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
