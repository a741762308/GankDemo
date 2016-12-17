package com.jsqix.dongqing.gank.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.gson.Gson;
import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.adapter.recycler.RecyclerRecAdapter;
import com.jsqix.dongqing.gank.api.Api;
import com.jsqix.dongqing.gank.bean.GankData;
import com.jsqix.dongqing.gank.bean.ImgDataBean;
import com.jsqix.utils.ACache;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dongqing on 2016/12/8.
 * 每日数据
 */

public class RecommendFragment extends BaseFragment {
    private RecyclerRecAdapter adapter;
    private List<GankData.ResultsBean> data = new ArrayList<>();

    final static long DAY_OF_MILLISECOND = 24 * 60 * 60 * 1000L;
    private Date mCurrentDate;

    public RecommendFragment() {
        // Required empty public constructor
    }


    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerRecAdapter(mContext, R.layout.item_recycler_rec, data);
        recyclerView.setAdapter(adapter);

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOverScrollBottomShow(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                page = 1;
                getData(new Date());
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                page++;
                getData(mCurrentDate);
            }
        });
    }

    private void getData(final Date date) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        Api.getInstance().service.getDayData(calender.get(Calendar.YEAR), calender.get(Calendar.MONTH) + 1, calender.get(Calendar.DAY_OF_MONTH))
                .subscribeOn(Schedulers.io())
                .map(new Func1<GankData, GankData.ResultsBean>() {
                    @Override
                    public GankData.ResultsBean call(GankData gankData) {
                        return gankData.getResults();
                    }
                })
//                .map(new Func1<GankData.ResultsBean, List<ImgDataBean>>() {
//
//                    @Override
//                    public List<ImgDataBean> call(GankData.ResultsBean resultsBean) {
//                        return addAllResults(resultsBean);
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankData.ResultsBean>() {

                    @Override
                    public void onCompleted() {
                        Log.v("success:", "onCompleted");
                        mCurrentDate = new Date(date.getTime() - DAY_OF_MILLISECOND);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("failure:", "onError");
                        refreshLayout.finishRefreshing();
                        refreshLayout.finishLoadmore();
                    }

                    @Override
                    public void onNext(GankData.ResultsBean resultsBean) {
                        if ("{}".equals(new Gson().toJson(resultsBean))) {
                            getData(new Date(date.getTime() - DAY_OF_MILLISECOND));
                        } else {
                            if (page == 1) {
                                data.clear();
                                refreshLayout.finishRefreshing();
                            } else {
                                refreshLayout.finishLoadmore();
                            }

                            data.add(resultsBean);
                            if (page == 1) {
                                adapter.refresh();
                                if (resultsBean.get福利() != null && resultsBean.get福利().size() > 0)
                                    ACache.get(mContext).put("imgUrl", resultsBean.get福利().get(0).getUrl());
                            } else {
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }

                });
    }

    private List<ImgDataBean> addAllResults(GankData.ResultsBean resultsBean) {
        return null;
    }
}
