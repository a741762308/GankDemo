package com.jsqix.dongqing.gank.adapter.recycler.multi;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jsqix.dongqing.gank.R;
import com.jsqix.dongqing.gank.bean.ImgDataBean;
import com.jsqix.dongqing.gank.utils.DateUtil;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by dongqing on 2016/12/9.
 */

public class MeiziDelagate implements ItemViewDelegate<ImgDataBean> {
    private Context mContext;

    public MeiziDelagate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_recycler_meizi;
    }

    @Override
    public boolean isForViewType(ImgDataBean item, int position) {
        return false;
    }


    @Override
    public void convert(ViewHolder holder, ImgDataBean imgDataBeann, int position) {
        ImageView image = holder.getView(R.id.image);
        Glide.with(mContext).load(imgDataBeann.getUrl()).crossFade().into(image);
        holder.setText(R.id.text, DateUtil.dateToString(imgDataBeann.getPublishedAt(), DateUtil.DATE_FORMAT_DISPLAY2));
    }


}
