package com.jsqix.dongqing.gank.adapter.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.WebViewActivity;
import com.jsqix.dongqing.gank.bean.youku.YKVideo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by dongqing on 2017/1/16.
 */

public class YoukuVideoAdapter extends CommonAdapter<YKVideo.VideosBean>{
    public YoukuVideoAdapter(Context context, int layoutId, List<YKVideo.VideosBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final YKVideo.VideosBean videosBean, int position) {
        holder.setText(R.id.text, videosBean.getTitle());
        holder.setOnClickListener(R.id.text, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("name", videosBean.getTitle());
                intent.putExtra("url", videosBean.getLink());
                mContext.startActivity(intent);
            }
        });
    }
}
