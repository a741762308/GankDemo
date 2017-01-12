package com.jsqix.dongqing.gank.adapter.recycler.multi;

import android.content.Context;

import com.jsqix.dongqing.gank.bean.gank.ImgDataBean;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by dongqing on 2016/12/9.
 */

public class RecyclerRecommandAdapter extends MultiItemTypeAdapter<ImgDataBean> {
    public RecyclerRecommandAdapter(Context context, List<ImgDataBean> datas) {
        super(context, datas);
        addItemViewDelegate(new MeiziDelagate(mContext));
    }
}
