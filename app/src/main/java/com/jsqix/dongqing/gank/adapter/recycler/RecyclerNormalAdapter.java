package com.jsqix.dongqing.gank.adapter.recycler;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.WebViewActivity;
import com.jsqix.dongqing.gank.bean.DataBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by dongqing on 2016/12/8.
 */

public class RecyclerNormalAdapter extends CommonAdapter<DataBean> {
    public RecyclerNormalAdapter(Context context, int layoutId, List<DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final DataBean categorylData, int position) {
        holder.setText(R.id.text, categorylData.getDesc());
        holder.setOnClickListener(R.id.text, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("name", categorylData.getDesc());
                intent.putExtra("url", categorylData.getUrl());
                mContext.startActivity(intent);
            }
        });
    }


}
