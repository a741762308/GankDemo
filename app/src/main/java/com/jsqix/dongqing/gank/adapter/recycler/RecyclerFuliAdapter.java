package com.jsqix.dongqing.gank.adapter.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jsqix.dongqing.gank.PhotoViewActivity;
import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.bean.DataBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by dongqing on 2016/12/8.
 */

public class RecyclerFuliAdapter extends CommonAdapter<DataBean> {
    public RecyclerFuliAdapter(Context context, int layoutId, List<DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final DataBean dataBean, int position) {
        ImageView image = holder.getView(R.id.image);
        Glide.with(mContext).load(dataBean.getUrl()).crossFade().into(image);
        holder.setOnClickListener(R.id.image, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PhotoViewActivity.class);
                intent.putExtra("url", dataBean.getUrl());
                mContext.startActivity(intent);
            }
        });
    }
}
