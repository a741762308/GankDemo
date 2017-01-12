package com.jsqix.dongqing.gank.adapter.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.WebViewActivity;
import com.jsqix.dongqing.gank.bean.gank.GankData;
import com.jsqix.dongqing.gank.bean.gank.ImgDataBean;
import com.jsqix.dongqing.gank.utils.DateUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by dongqing on 2016/12/8.
 */

public class RecyclerRecAdapter extends CommonAdapter<GankData.ResultsBean> {
    private LinearLayout category, content;

    public RecyclerRecAdapter(Context context, int layoutId, List<GankData.ResultsBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, GankData.ResultsBean resultsBean, int position) {
        if (resultsBean.get福利().size() > 0) {
            ImageView fuli = holder.getView(R.id.image);
            String imgUrl = resultsBean.get福利().get(0).getUrl();
            Glide.with(mContext).load(imgUrl).crossFade().centerCrop().into(fuli);
            holder.setText(R.id.text, DateUtil.dateToString(resultsBean.get福利().get(0).getPublishedAt(), DateUtil.DATE_FORMAT_DISPLAY2));
        }
        addView(holder, resultsBean.getAndroid(), "Android");
        addView(holder, resultsBean.getiOS(), "iOS");
        addView(holder, resultsBean.get前端(), "前端");
        addView(holder, resultsBean.get瞎推荐(), "瞎推荐");
        addView(holder, resultsBean.get拓展资源(), "拓展资源");
        addView(holder, resultsBean.get休息视频(), "休息视频");
    }

    private void addView(ViewHolder holder, List<ImgDataBean> list, String title) {
        if (list != null && list.size() > 0) {
            category = holder.getView(R.id.category);
            View outView = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_category, null);
            TextView head = (TextView) outView.findViewById(R.id.head);
            content = (LinearLayout) outView.findViewById(R.id.content);
            head.setText(title);
            for (final ImgDataBean bean : list) {
                View innerView = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_android, null);
                final ImageView image = (ImageView) innerView.findViewById(R.id.image);
                TextView name = (TextView) innerView.findViewById(R.id.text);
                if (bean.getImages() != null && bean.getImages().size() > 0) {
                    image.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(bean.getImages().get(0)).crossFade().into(image);

                } else {
                    image.setVisibility(View.GONE);
                }
                name.setText(bean.getDesc());
                name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra("name", bean.getDesc());
                        intent.putExtra("url", bean.getUrl());
                        mContext.startActivity(intent);
                    }
                });

                content.addView(innerView);
            }
            category.addView(outView);
        }
    }

    public void refresh() {
        if (content != null) {
            content.removeAllViewsInLayout();
        }
        if (category != null) {
            category.removeAllViewsInLayout();
        }
        notifyDataSetChanged();
    }
}