package com.jsqix.dongqing.gank.fragment.youku;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.YoukuPlayerActivity;
import com.jsqix.dongqing.gank.adapter.recycler.YoukuProgramAdapter;
import com.jsqix.dongqing.gank.api.Api;
import com.jsqix.dongqing.gank.bean.youku.YKProgram;
import com.jsqix.dongqing.gank.fragment.RefreshFragment;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dongqing on 2017/1/16.
 */

public class YoukuProgramFragment extends RefreshFragment {
    private List<YKProgram.ShowsBean> data = new ArrayList<>();
    private YoukuProgramAdapter adapter;

    @Override
    protected void initView() {
        GridLayoutManager manager = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new YoukuProgramAdapter(mContext, R.layout.item_recycler_yk_program, data);
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
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                YKProgram.ShowsBean showsBean=data.get(position);
                Intent intent=new Intent(mContext, YoukuPlayerActivity.class);
                intent.putExtra("id",showsBean.getId());
                intent.putExtra("url",showsBean.getPlay_link());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    private void getData() {
        Api.getInstance().YOUKU.service.getYkProgrammeByCategory("655690f8143987c6", categoryType, page, Api.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .map(new Func1<YKProgram, List<YKProgram.ShowsBean>>() {

                    @Override
                    public List<YKProgram.ShowsBean> call(YKProgram ykProgram) {
                        return ykProgram.getShows();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<YKProgram.ShowsBean>>() {
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
                    public void onNext(List<YKProgram.ShowsBean> showsBean) {
                        Log.v("result:", new Gson().toJson(showsBean));
                        if (page == 1) {
                            data.clear();
                            refreshLayout.finishRefreshing();
                        } else {
                            refreshLayout.finishLoadmore();
                        }
                        data.addAll(showsBean);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
